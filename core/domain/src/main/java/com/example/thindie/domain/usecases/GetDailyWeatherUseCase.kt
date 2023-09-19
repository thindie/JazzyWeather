package com.example.thindie.domain.usecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import javax.inject.Inject
import javax.inject.Named

class GetDailyWeatherUseCase @Inject constructor(@Named("repository") private val repository: WeatherRepository) {
    suspend operator fun invoke(forecastAble: ForecastAble): Result<WeatherDaily> {
        return repository.getDailyWeather(forecastAble)
    }

}