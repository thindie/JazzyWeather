package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly

class GetHourlyWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(forecastAble: ForecastAble): Result<WeatherHourly> {
        return repository.getHourlyWeather(forecastAble)
    }

}