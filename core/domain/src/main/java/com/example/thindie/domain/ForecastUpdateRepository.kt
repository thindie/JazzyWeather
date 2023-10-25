package com.example.thindie.domain

import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly

interface ForecastUpdateRepository {
    suspend fun update()
    suspend fun fetchSite(forecastAble: ForecastAble)

    suspend fun getHourlyWeatherByDate(date: String, forecastAble: ForecastAble): WeatherHourly

}