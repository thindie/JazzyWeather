package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastUpdateRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject

class GetHourlyWeatherByDateUseCase @Inject
constructor(private val repository: ForecastUpdateRepository) {
    suspend operator fun invoke(date: String, forecastAble: ForecastAble): WeatherHourly? {
        return repository.getHourlyWeatherByDate(date, forecastAble)

    }
}