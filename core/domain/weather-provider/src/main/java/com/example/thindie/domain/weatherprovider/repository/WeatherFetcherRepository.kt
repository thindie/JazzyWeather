package com.example.thindie.domain.weatherprovider.repository

import com.example.thindie.domain.weatherprovider.contract.WeatherFetchRequirements
import com.example.thindie.domain.weatherprovider.entity.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherFetcherRepository {
    suspend fun fetchWeather(requirements: WeatherFetchRequirements): Result<Weather>
    fun fetchPinnedWeatherLocations(): Flow<Result<List<Weather>>>
    suspend fun pinWeather(requirements: WeatherFetchRequirements)
    suspend fun updateSavedWeatherPlaces()
    suspend fun deleteWeather(place: String)
}