package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.WeatherHourly

class GetHourlyWeatherListUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(): Result<List<WeatherHourly>> {
        return repository.getWeatherHourlyList()
    }

}