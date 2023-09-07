package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository

class DeleteWeatherSiteUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(place: String) {
        return repository.deleteWeather(place)
    }

}