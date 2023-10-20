package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Singleton
internal class WeatherDailyRepositoryImpl @Inject constructor(
    private val dailyDao: WeatherDailyDao,
    private val hourlyDao: WeatherHourlyDao,
    private val apiService: WeatherApiService,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : ForecastAbleRepository<WeatherDaily> {

    private val place = MutableStateFlow<String?>(null)

    private val forecastAble = callbackFlow {
        launch {
            val fromBase = IO {
                dailyDao.getWeatherSite(place = place.value.orEmpty())
            }
            send(fromBase.map())
        }
        awaitClose {
           // place.tryEmit(null)
        }
    }.flowOn(ioDispatcher)

    override suspend fun requestForecastAble(forecastAble: ForecastAble) {
        place.value = forecastAble.getSight()
    }

    override suspend fun requestConcreteDateForecastAble(
        simpleIso8106: String,
        forecastAble: ForecastAble,
    ) {
        IO {

        }
    }

    override fun observeForecastAbleList(): Flow<List<WeatherDaily>> {
        return flow { }
    }

    override fun observeForecastAble(): Flow<WeatherDaily> {
        return forecastAble
            .flowOn(ioDispatcher)
    }

    override suspend fun deleteForecastAble(place: String) {
        IO {
            dailyDao.deleteWeatherSite(place)
        }

    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {
        val daily = IO {
            dailyDao.getWeatherSite(
                forecastAble.getSightLatitude().toDouble(),
                forecastAble.getSightLongitude().toDouble()
            )
        }

        deleteForecastAble(daily.place)
        IO {
            dailyDao.upsertWeatherSite(daily.copy(place = forecastAble.getSight()))
        }

    }

    private suspend fun <T> IO(action: suspend () -> T): T {
        return withContext(ioDispatcher) {
            action.invoke()
        }
    }

}