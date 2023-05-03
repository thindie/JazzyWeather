package com.example.thindie.domain.weatherprovider.usecase

import com.example.thindie.domain.weatherprovider.contract.WeatherOperator
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.domain.weatherprovider.repository.WeatherFetcherRepository
import javax.inject.Inject

internal class FetchWeatherUseCase @Inject constructor(private val repository: WeatherFetcherRepository): WeatherOperator {
    override suspend fun fetchWeather(city: String): Weather {
       return repository.fetchWeather(city)
    }

    override suspend fun fetchPinnedWeatherLocations(): List<Weather> {
       return repository.fetchPinnedWeatherLocations()
    }

    override suspend fun pinWeather(city: String) {
        repository.pinWeather(city)
    }
}