package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import javax.inject.Inject
import javax.inject.Named

class RememberWeatherSiteUseCase @Inject constructor(@Named("repository") private val repository: WeatherRepository) {
    suspend operator fun invoke(forecastAble: ForecastAble) {
        repository.rememberWeatherLocation(forecastAble)
    }
}