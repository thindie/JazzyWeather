package com.example.thindie.domain.weatherprovider.repository

import com.example.thindie.domain.weatherprovider.entity.Weather

interface WeatherFetcherRepository {
    suspend fun fetchWeather(city: String): Weather
}