package com.example.thindie.domain

import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly

interface WeatherRepository {
    suspend fun getDailyWeather(forecastAble: ForecastAble): Result<WeatherDaily>

    suspend fun getHourlyWeather(forecastAble: ForecastAble): Result<WeatherHourly>

    suspend fun getWeatherDailyList(): Result<List<WeatherDaily>>

    suspend fun getWeatherHourlyList(): Result<List<WeatherHourly>>

    suspend fun deleteWeather(place: String)

    suspend fun rememberWeatherLocation(forecastAble: ForecastAble)

}