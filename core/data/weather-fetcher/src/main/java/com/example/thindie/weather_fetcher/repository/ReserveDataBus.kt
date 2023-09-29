package com.example.thindie.weather_fetcher.repository

import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherDailyDbModel
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.database.room.WeatherHourlyDbModel
import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ReserveDataBus @Inject constructor(
    private val daoHourly: WeatherHourlyDao,
    private val daoDaily: WeatherDailyDao,
) : WeatherRepository {
    override suspend fun getDailyWeather(forecastAble: ForecastAble): Result<WeatherDaily> {
        return Result.success(daoDaily.getWeatherSite(forecastAble.getSight()).map())
    }


    override suspend fun getHourlyWeather(forecastAble: ForecastAble): Result<WeatherHourly> {
        return Result.success(daoHourly.getWeatherSite(forecastAble.getSight()).map())
    }

    override suspend fun getWeatherDailyList(): Result<List<WeatherDaily>> {
        return Result.success(daoDaily.getAllWeathersSite().map { it.map() })
    }

    override suspend fun requestWeatherHourly() {}

    override fun observeWeatherHourlyList(): Flow<List<WeatherHourly>> {
        return daoHourly.observeAllWeathersSite().map { it.map { it.map() } }
    }


    override suspend fun deleteWeather(place: String) {
        daoDaily.deleteWeatherSite(daoDaily.getWeatherSite(place))
        daoHourly.deleteWeatherSite(daoHourly.getWeatherSite(place))
    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {

        val weatherDaily = getDailyDbModelByCoordinates(forecastAble)
        val weatherHourly = getHourlyDbModelByCoordinates(forecastAble)

        if (weatherDaily != null) {
            deleteDaily(weatherDaily)
            reNewDaily(forecastAble, weatherDaily)
        }

        if (weatherHourly != null) {
            deleteHourly(weatherHourly)
            reNewHourly(forecastAble, weatherHourly)
        }

    }

    private suspend fun getHourlyDbModelByCoordinates(
        forecastAble: ForecastAble,
    ): WeatherHourlyDbModel? {
        return try {
            daoHourly.getWeatherSite(
                forecastAble.getSightLatitude().toDouble(),
                forecastAble.getSightLongitude().toDouble()
            )
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun getDailyDbModelByCoordinates(
        forecastAble: ForecastAble,
    ): WeatherDailyDbModel? {
        return try {
            daoDaily.getWeatherSite(
                forecastAble.getSightLatitude().toDouble(),
                forecastAble.getSightLongitude().toDouble()
            )
        } catch (_: Exception) {
            null
        }
    }

    private suspend fun deleteHourly(dbModel: WeatherHourlyDbModel?) {
        try {
            if (dbModel != null) {
                daoHourly.deleteWeatherSite(dbModel)
            }
        } catch (_: Exception) {
        }
    }

    private suspend fun deleteDaily(dbModel: WeatherDailyDbModel?) {
        if (dbModel != null) {
            daoDaily.deleteWeatherSite(dbModel)
        }
    }

    private suspend fun reNewDaily(
        forecastAble: ForecastAble,
        weatherDailyDbModel: WeatherDailyDbModel,
    ) {
        daoDaily.upsertWeatherSite(weatherDailyDbModel.copy(forecastAble.getSight()))
    }

    private suspend fun reNewHourly(
        forecastAble: ForecastAble,
        weatherHourlyDbModel: WeatherHourlyDbModel,
    ) {
        daoHourly.upsertWeatherSite(weatherHourlyDbModel.copy(forecastAble.getSight()))
    }
}