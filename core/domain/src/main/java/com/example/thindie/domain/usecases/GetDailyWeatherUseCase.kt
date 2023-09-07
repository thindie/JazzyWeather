package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily

class GetDailyWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(forecastAble: ForecastAble): Result<WeatherDaily> {
        return repository.getDailyWeather(forecastAble)
    }

}