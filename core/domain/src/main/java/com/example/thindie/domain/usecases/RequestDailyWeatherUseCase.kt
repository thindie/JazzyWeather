package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import javax.inject.Inject
import javax.inject.Named

class RequestDailyWeatherUseCase @Inject
constructor( private val repository: ForecastAbleRepository<WeatherDaily>) {
    suspend operator fun invoke(forecastAble: ForecastAble) {
        repository.requestForecastAble(forecastAble)
    }

}