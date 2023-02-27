package com.example.jazzyweather.domain

import kotlinx.coroutines.flow.Flow

interface JazzyWeatherRepository {
    fun searchAndSelectLocation(location: String): Flow<Results<List<Possibility>>>
    suspend fun requestWeather(possibility: Possibility): Weather
    suspend fun addToFavorites(weather: Weather)
    suspend fun deleteFromFavorites(id: String)
}