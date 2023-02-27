package com.example.jazzyweather.data

import com.example.jazzyweather.data.local.FavoriteWeatherDao
import com.example.jazzyweather.data.remote.WeatherApiService
import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Results
import com.example.jazzyweather.domain.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val favoriteWeatherDao: FavoriteWeatherDao,
    private val weatherApiService: WeatherApiService,
    private val producer: PlaceDetector
) : JazzyWeatherRepository{
    override fun searchAndSelectLocation(location: String): Flow<Results<List<Possibility>>> {
        TODO("Not yet implemented")
    }

    override suspend fun requestWeather(possibility: Possibility): Weather {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorites(weather: Weather) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavorites(id: String) {
        TODO("Not yet implemented")
    }

}