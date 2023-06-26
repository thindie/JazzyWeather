package com.example.thindie.domain.weatherprovider.repository

import com.example.thindie.domain.weatherprovider.contract.WeatherFetchRequirements
import com.example.thindie.domain.weatherprovider.entity.Weather

interface WeatherFetcherRepository {
    suspend fun fetchWeather(requirements: WeatherFetchRequirements): Result<Weather>
    suspend fun fetchPinnedWeatherLocations(): Result<List<Weather>>
    suspend fun pinWeather(city: String)
}