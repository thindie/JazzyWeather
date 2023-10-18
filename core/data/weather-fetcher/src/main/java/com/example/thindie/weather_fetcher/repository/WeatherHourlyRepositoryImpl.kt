package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.core.network.dto.hourlydto.map
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.database.room.WeatherHourlyDbModel
import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.FetchUnit
import com.example.thindie.weather_fetcher.mappers.map
import com.example.thindie.weather_fetcher.toFetchUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@Singleton
internal class WeatherHourlyRepositoryImpl @Inject constructor(
    private val hourlyDao: WeatherHourlyDao,
    private val apiService: WeatherApiService,
    @Named("fetchController") private val fetchController: RatificationObserver,
    @Named("networkController") private val netWorkController: RatificationObserver,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : ForecastAbleRepository<WeatherHourly> {

    private val fetchQueue: MutableStateFlow<FetchUnit?> = MutableStateFlow(null)

    private val state: Flow<RepositoryState> =
        combine(
            fetchQueue,
            fetchController.observeRatification(),
            netWorkController.observeRatification(),
            hourlyDao.observeAllWeathersSite(),
        ) { fetchUnit, fetchController, networkController, storedUnits ->

            if (networkController.isAllowed()) {
                if (fetchUnit?.concreteTime != null) {
                    apiService.getHourlyWeatherByDate(
                        iso8106String = fetchUnit.concreteTime,
                        latitude = fetchUnit.latitude,
                        longitude = fetchUnit.longitude,
                        timeZone = fetchUnit.timezone
                    )
                }
                if (fetchController.isAllowed()) {
                    updateStored(storedUnits)
                }
            }

            RepositoryState(
                forecastAble = null,
                isFetchAllowed = fetchController.isAllowed(),
                isNetworkAvailAble = networkController.isAllowed(),
                foreCastAbleList = storedUnits.map { it.map() }
            )
        }


    override suspend fun requestForecastAble(forecastAble: ForecastAble) {
        fetchQueue.value = forecastAble.toFetchUnit(null)
    }

    override suspend fun requestConcreteDateForecastAble(
        simpleIso8106: String,
        forecastAble: ForecastAble,
    ) {
        fetchQueue.value = forecastAble.toFetchUnit(simpleIso8106)
    }

    override fun observeForecastAbleList(): Flow<List<WeatherHourly>> {
        return state.map { state ->
            state
                .foreCastAbleList
                .filter { forecastAble ->
                    forecastAble::class.java.isAssignableFrom(WeatherHourly::class.java)
                }
                .map { forecastAble ->
                    forecastAble as WeatherHourly
                }
        }.flowOn(ioDispatcher)
    }

    override fun observeForecastAble(): Flow<WeatherHourly> {
        return state
            .filterNot { state ->
                state.forecastAble == null
            }.map { state ->
                requireNotNull(state.forecastAble)
                require(state.forecastAble::class.java.isAssignableFrom(WeatherHourly::class.java))
                state.forecastAble as WeatherHourly
            }.flowOn(ioDispatcher)
    }


    override suspend fun deleteForecastAble(place: String) {
        hourlyDao.deleteWeatherSite(place)
    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {
        if (forecastAble::class.java.isAssignableFrom(WeatherHourly::class.java)) {
            forecastAble as WeatherHourly
            hourlyDao.upsertWeatherSite(forecastAble.map())
        }
    }

    private suspend fun updateStored(list: List<WeatherHourlyDbModel>) {
        list.forEach {
            val fetched = apiService.getHourlyWeather(
                latitude = it.latitude.toFloat(),
                longitude = it.longitude.toFloat(),
                timeZone = it.timezone
            )
            hourlyDao.upsertWeatherSite(fetched.map().map(it.map()).map())
        }
    }


}