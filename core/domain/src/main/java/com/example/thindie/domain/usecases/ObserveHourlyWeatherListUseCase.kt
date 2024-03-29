package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow

class ObserveHourlyWeatherListUseCase @Inject
constructor(private val repository: ForecastAbleRepository<WeatherHourly>) {
    operator fun invoke(): Flow<List<WeatherHourly>> {
        return repository.observeForecastAbleList()
    }

}