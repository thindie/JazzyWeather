package com.example.jazzyweather.domain

import kotlinx.coroutines.flow.Flow

interface JazzyWeatherRepository {
    fun searchAndSelectLocation(location: String): Flow<Results<List<Possibility>>>

    suspend fun getFavoriteWeatherLocations(): Results<List<Weather>>
    suspend fun requestWeather(possibility: Possibility): Results<Weather>
    suspend fun addToFavorites(weather: Weather)
    suspend fun deleteFromFavorites(id: String)
    fun getOfflineWeathers(): Flow<Results<List<WeatherOffline>>>
}