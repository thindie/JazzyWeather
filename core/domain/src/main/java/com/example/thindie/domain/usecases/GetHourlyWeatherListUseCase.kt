package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named

class GetHourlyWeatherListUseCase @Inject constructor(@Named("repository") private val repository: WeatherRepository) {
    suspend operator fun invoke(): Result<List<WeatherHourly>> {
        return repository.getWeatherHourlyList()
    }

}