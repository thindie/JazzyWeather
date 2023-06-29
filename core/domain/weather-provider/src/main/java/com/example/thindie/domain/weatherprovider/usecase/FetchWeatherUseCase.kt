package com.example.thindie.domain.weatherprovider.usecase

import com.example.thindie.domain.weatherprovider.contract.WeatherFetchRequirements
import com.example.thindie.domain.weatherprovider.contract.WeatherOperator
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.domain.weatherprovider.repository.WeatherFetcherRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

internal class FetchWeatherUseCase @Inject constructor(private val repository: WeatherFetcherRepository) :
    WeatherOperator {
    override suspend fun fetchWeather(requirements: WeatherFetchRequirements): Result<Weather> {
        return repository.fetchWeather(requirements)
    }

    override fun fetchPinnedWeatherLocations(): Flow<Result<List<Weather>>> {
        return repository.fetchPinnedWeatherLocations()
    }

    override suspend fun pinWeather(requirements: WeatherFetchRequirements) {
        repository.pinWeather(requirements)
    }

    override suspend fun updateSavedWeatherPlaces() {
        repository.updateSavedWeatherPlaces()
    }

    override suspend fun deleteWeather(place: String) {
        repository.deleteWeather(place)
    }
}