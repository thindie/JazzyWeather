package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named

class ReserveWeatherInteractor @Inject constructor(@Named("reserveBus") private val repository: WeatherRepository) {
    suspend fun getWeatherHourlyReserveList(): List<WeatherHourly> {
        return repository.getWeatherHourlyList().getOrElse { emptyList() }
    }
}