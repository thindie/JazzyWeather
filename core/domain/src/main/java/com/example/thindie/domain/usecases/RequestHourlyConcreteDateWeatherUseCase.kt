package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named

class RequestHourlyConcreteDateWeatherUseCase @Inject
constructor( private val repository: ForecastAbleRepository<WeatherHourly>) {
    suspend operator fun invoke(forecastAble: ForecastAble, dateIso8106: String) {
        repository.requestConcreteDateForecastAble(dateIso8106, forecastAble)
    }

}