package com.example.thindie.weather_fetcher.repository

import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.core.network.dto.dailydto.map
import com.example.thindie.core.network.dto.hourlydto.map
import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class WeatherRepositoryImpl @Inject constructor(
    private val dailyDao: WeatherDailyDao,
    private val hourlyDao: WeatherHourlyDao,
    private val apiService: WeatherApiService,
    @DispatchersIOModule.IODispatcher private val ioDispatcher: CoroutineDispatcher,
) : WeatherRepository {
    override suspend fun getDailyWeather(forecastAble: ForecastAble): Result<WeatherDaily> {
        return Result.success(
            apiService.getDailyWeather(
                latitude = forecastAble.getSightLatitude(),
                longitude = forecastAble.getSightLongitude(),
                timeZone = forecastAble.getTimeZone(),
            )
        ).mapCatching { response ->
            response.map()
        }.mapCatching { dailyDto ->
            dailyDto.map(forecastAble)
        }.onSuccess { dailyDomain ->
            dailyDao.upsertWeatherSite(dailyDomain.map())
        }
    }

    override suspend fun getHourlyWeather(forecastAble: ForecastAble): Result<WeatherHourly> {
        return Result.success(
            apiService.getHourlyWeather(
                latitude = forecastAble.getSightLatitude(),
                longitude = forecastAble.getSightLongitude(),
                timeZone = forecastAble.getTimeZone(),
            )
        ).mapCatching { response ->
            response.map()
        }.mapCatching { hourlyDto ->
            hourlyDto.map(forecastAble)
        }.onSuccess {
            hourlyDao.upsertWeatherSite(it.map())
        }
    }

    override suspend fun getWeatherDailyList(): Result<List<WeatherDaily>> {
        return Result.success(
            dailyDao.getAllWeathersSite()
        ).mapCatching { list ->
            list.map {
                val weather = withContext(ioDispatcher) {
                    apiService.getDailyWeather(
                        latitude = it.latitude.toFloat(),
                        longitude = it.longitude.toFloat(),
                        timeZone = it.timezone,
                    )
                }
                weather
                    .map()
                    .map(object : ForecastAble {
                        override fun getSight() = it.place
                        override fun getSightLatitude() = it.latitude.toFloat()
                        override fun getSightLongitude() = it.longitude.toFloat()
                        override fun getTimeZone() = it.timezone
                    })
            }
        }
    }

    override suspend fun getWeatherHourlyList(): Result<List<WeatherHourly>> {
        return Result.success(
            hourlyDao.getAllWeathersSite()
        ).mapCatching { list ->
            list.map {
                val weather = withContext(ioDispatcher) {
                    apiService.getHourlyWeather(
                        latitude = it.latitude.toFloat(),
                        longitude = it.longitude.toFloat(),
                        timeZone = it.timezone,
                    )
                }
                weather
                    .map()
                    .map(object : ForecastAble {
                        override fun getSight() = it.place
                        override fun getSightLatitude() = it.latitude.toFloat()
                        override fun getSightLongitude() = it.longitude.toFloat()
                        override fun getTimeZone() = it.timezone
                    })
            }
        }
    }

    override suspend fun deleteWeather(place: String) {
        dailyDao.deleteWeatherSite(dailyDao.getWeatherSite(place))
        hourlyDao.deleteWeatherSite(hourlyDao.getWeatherSite(place))
    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {
        withContext(ioDispatcher) {
            getHourlyWeather(forecastAble)
            getDailyWeather(forecastAble)
        }
    }
}