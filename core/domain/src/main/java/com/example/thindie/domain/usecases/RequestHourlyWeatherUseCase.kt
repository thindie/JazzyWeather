package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named

class RequestHourlyWeatherUseCase @Inject
constructor( private val repository: ForecastAbleRepository<WeatherHourly>) {
    suspend operator fun invoke(forecastAble: ForecastAble) {
        repository.requestForecastAble(forecastAble)
    }

}