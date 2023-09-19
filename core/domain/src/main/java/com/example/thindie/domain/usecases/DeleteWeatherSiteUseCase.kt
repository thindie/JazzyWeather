package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import javax.inject.Inject
import javax.inject.Named

class DeleteWeatherSiteUseCase @Inject constructor(@Named("repository") private val repository: WeatherRepository) {
    suspend operator fun invoke(place: String) {
        return repository.deleteWeather(place)
    }

}