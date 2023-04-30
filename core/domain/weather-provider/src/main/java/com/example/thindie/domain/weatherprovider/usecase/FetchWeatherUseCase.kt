package com.example.thindie.domain.weatherprovider.usecase

import com.example.thindie.domain.weatherprovider.contract.WeatherFetcher
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.domain.weatherprovider.repository.WeatherFetcherRepository
import javax.inject.Inject

internal class FetchWeatherUseCase @Inject constructor(private val repository: WeatherFetcherRepository): WeatherFetcher {
    override suspend fun fetchWeather(city: String): Weather {
       return repository.fetchWeather(city)
    }
}