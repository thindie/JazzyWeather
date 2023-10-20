package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.Flow

class ObserveDailyWeatherUseCase @Inject
constructor( private val repository: ForecastAbleRepository<WeatherDaily>) {
    operator fun invoke(): Flow<WeatherDaily> {
        return repository.observeForecastAble()
    }

}