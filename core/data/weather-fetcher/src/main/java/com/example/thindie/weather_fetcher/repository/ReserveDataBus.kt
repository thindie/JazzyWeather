package com.example.thindie.weather_fetcher.repository

import com.example.thindie.database.room.WeatherDailyDao
import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weather_fetcher.mappers.map
import javax.inject.Inject


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

    override suspend fun getWeatherHourlyList(): Result<List<WeatherHourly>> {
        return Result.success(daoHourly.getAllWeathersSite().map { it.map() })
    }

    override suspend fun deleteWeather(place: String) {
        daoDaily.deleteWeatherSite(daoDaily.getWeatherSite(place))
        daoHourly.deleteWeatherSite(daoHourly.getWeatherSite(place))
    }

    override suspend fun rememberWeatherLocation(forecastAble: ForecastAble) {

    }
}