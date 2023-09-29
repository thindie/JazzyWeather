package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow

class ObserveHourlyWeatherListUseCase @Inject constructor(@Named("repository") private val repository: WeatherRepository) {
    operator fun invoke(): Flow<List<WeatherHourly>> {
        return repository.observeWeatherHourlyList()
    }

}