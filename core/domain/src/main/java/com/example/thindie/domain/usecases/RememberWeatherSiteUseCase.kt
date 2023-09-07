package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import javax.inject.Inject

class RememberWeatherSiteUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(forecastAble: ForecastAble) {
        repository.rememberWeatherLocation(forecastAble)
    }
}