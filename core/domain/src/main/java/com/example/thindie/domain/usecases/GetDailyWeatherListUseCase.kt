package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily

class GetDailyWeatherListUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(): Result<List<WeatherDaily>> {
        return repository.getWeatherDailyList()
    }

}