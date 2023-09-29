package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import javax.inject.Inject
import javax.inject.Named

class RequestHourlyWeatherListUseCase @Inject constructor(@Named("repository") private val repository: WeatherRepository) {
    suspend operator fun invoke() {
        return repository.requestWeatherHourly()
    }

}