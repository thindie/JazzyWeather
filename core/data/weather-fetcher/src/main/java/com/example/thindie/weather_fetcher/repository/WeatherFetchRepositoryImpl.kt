package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.localrawresources.di.DispatchersIOModule
import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.dto.dailydto.map
import com.example.thindie.core.network.dto.hourlydto.map
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.EventTransmitter
import com.example.thindie.domain.ForecastUpdateRepository
import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.EventKind
import com.example.thindie.weather_fetcher.FetchPermission
import com.example.thindie.weather_fetcher.mappers.KindEvent
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Singleton
internal class WeatherFetchRepositoryImpl @Inject constructor(
    private val dao: WeatherHourlyDao,
    private val dailyDao: WeatherDailyDao,
    private val service: WeatherApiService,
    private val eventTransmitter: EventTransmitter<EventKind>,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    RatificationObserver(), ForecastUpdateRepository {
    override fun observeRatification(): Flow<RatificationAble> {
        return dao.isEmpty().map { isEmpty ->
            if (isEmpty) {
                eventTransmitter.send(KindEvent(EventKind.EMPTY))
            }
            FetchPermission(isEmpty.not())
        }
    }

    override suspend fun update() {
        try {
            updateDaily()
            updateHourly()
        } catch (_: Exception) {
            eventTransmitter.send(KindEvent(EventKind.NET))
            delay(3000)
            eventTransmitter.send(KindEvent(EventKind.CLOSE_EVENT))
        }
    }

    override suspend fun fetchSite(forecastAble: ForecastAble) {
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
            eventTransmitter.send(KindEvent(EventKind.NET))
            delay(3000)
            eventTransmitter.send(KindEvent(EventKind.CLOSE_EVENT))
        }

    }


    override suspend fun getHourlyWeatherByDate(
        date: String,
        forecastAble: ForecastAble,
    ): WeatherHourly? {
        return withContext(ioDispatcher) {
            try {
                service.getHourlyWeatherByDate(
                    iso8106String = date,
                    latitude = forecastAble.getSightLatitude(),
                    longitude = forecastAble.getSightLongitude(),
                    timeZone = forecastAble.getTimeZone()
                ).map()
                    .map(forecastAble)
            } catch (_: Exception) {
                eventTransmitter.send(KindEvent(EventKind.NET))
                delay(3000)
                eventTransmitter.send(KindEvent(EventKind.CLOSE_EVENT))
                null
            }

        }
    }


    private suspend fun updateHourly() {
        withContext(ioDispatcher) {
            try {
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
            } catch (_: Exception) {
                eventTransmitter.send(KindEvent(EventKind.NET))
                delay(3000)
                eventTransmitter.send(KindEvent(EventKind.CLOSE_EVENT))
                return@withContext
            }
        }
    }

    private suspend fun updateDaily() {
        withContext(ioDispatcher) {
            try {
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
            } catch (_: Exception) {
                eventTransmitter.send(KindEvent(EventKind.NET))
                delay(3000)
                eventTransmitter.send(KindEvent(EventKind.CLOSE_EVENT))
            }
        }
    }

}