package com.example.thindie.core.network

import com.example.thindie.core.network.dto.WeatherDto
import com.example.thindie.core.network.dto.WeatherHourlyDto

interface WeatherProvider {
    suspend fun provideDailyWeather(contract: WeatherProviderContract): WeatherDto
    suspend fun provideHourlyWeather(contract: WeatherProviderContract): WeatherHourlyDto
}

interface WeatherProviderContract {
    val latitude: Float
    val longitude: Float
    val timeZone: String
}