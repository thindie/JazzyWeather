package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.localrawresources.di.DispatchersIOModule
import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.dto.dailydto.map
import com.example.thindie.core.network.dto.hourlydto.map
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.ForecastUpdateRepository
import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.FetchPermission
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

internal class WeatherFetchRepositoryImpl @Inject constructor(
    private val dao: WeatherHourlyDao,
    private val dailyDao: WeatherDailyDao,
    private val service: WeatherApiService,
    @Named("networkController") private val fetchController: RatificationObserver,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    RatificationObserver(), ForecastUpdateRepository {
    override fun observeRatification(): Flow<RatificationAble> {
        return dao.isEmpty().map { isEmpty ->
            FetchPermission(isEmpty.not())
        }
    }

    override suspend fun update() {
        fetchController
            .observeRatification()
            .onEach { ratification ->
                if (ratification.isAllowed()) {
                    updateDaily()
                    updateHourly()
                }
            }
            .collect()
    }

    override suspend fun fetchSite(forecastAble: ForecastAble) {

        fetchController.observeRatification()
            .collectLatest {
                if (it.isAllowed()) {
                    val latitude = forecastAble.getSightLatitude()
                    val longitude = forecastAble.getSightLongitude()
                    val timezone = forecastAble.getTimeZone()
                    try {
                        withContext(ioDispatcher) {
                            service.getHourlyWeather(latitude, longitude, timeZone = timezone)
                                .map()
                                .map(forecastAble)
                                .map()
                                .apply { dao.upsertWeatherSite(this) }
                        }
                        withContext(ioDispatcher) {
                            service.getDailyWeather(latitude, longitude, timeZone = timezone)
                                .map()
                                .map(forecastAble)
                                .map()
                                .apply { dailyDao.upsertWeatherSite(this) }
                        }
                    } catch (_: Exception) {
                    }

                }
            }
    }


    override suspend fun getHourlyWeatherByDate(
        date: String,
        forecastAble: ForecastAble,
    ): WeatherHourly {
        return withContext(ioDispatcher) {
            service.getHourlyWeatherByDate(
                iso8106String = date,
                latitude = forecastAble.getSightLatitude(),
                longitude = forecastAble.getSightLongitude(),
                timeZone = forecastAble.getTimeZone()
            ).map()
                .map(forecastAble)
        }

    }


    private suspend fun updateHourly() {
        withContext(ioDispatcher) {
            dailyDao.getAllWeathersSite()
                .forEach { weatherDbModel ->
                    service.getDailyWeather(
                        latitude = weatherDbModel.latitude.toFloat(),
                        longitude = weatherDbModel.longitude.toFloat(),
                        timeZone = weatherDbModel.timezone
                    ).apply {
                        map()
                            .map(object : ForecastAble {
                                override fun getSight() =
                                    weatherDbModel.place

                                override fun getSightLatitude() =
                                    weatherDbModel.latitude.toFloat()

                                override fun getSightLongitude() =
                                    weatherDbModel.longitude.toFloat()

                                override fun getTimeZone() = weatherDbModel.timezone

                            }).map().apply {
                                dailyDao.upsertWeatherSite(this)
                            }
                    }
                }
        }
    }

    private suspend fun updateDaily() {
        withContext(ioDispatcher) {
            dao.getAllWeathersSite()
                .forEach { weatherDbModel ->
                    service.getHourlyWeather(
                        latitude = weatherDbModel.latitude.toFloat(),
                        longitude = weatherDbModel.longitude.toFloat(),
                        timeZone = weatherDbModel.timezone
                    ).apply {
                        map()
                            .map(object : ForecastAble {
                                override fun getSight() =
                                    weatherDbModel.place

                                override fun getSightLatitude() =
                                    weatherDbModel.latitude.toFloat()

                                override fun getSightLongitude() =
                                    weatherDbModel.longitude.toFloat()

                                override fun getTimeZone() = weatherDbModel.timezone

                            }).map().apply {
                                dao.upsertWeatherSite(this)
                            }
                    }
                }
        }
    }

}