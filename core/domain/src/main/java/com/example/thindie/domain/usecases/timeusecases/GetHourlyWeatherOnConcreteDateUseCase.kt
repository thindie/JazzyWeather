package com.example.thindie.domain.usecases.timeusecases

import com.example.thindie.domain.WeatherRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named

class GetHourlyWeatherOnConcreteDateUseCase
@Inject constructor(@Named("repository") private val repository: WeatherRepository) {
    suspend operator fun invoke(
        forecastAble: ForecastAble,
        simpleDate: String,
    ): Result<WeatherHourly> {
        return repository.getDailyWeatherOnConcreteDate(simpleDate, forecastAble)
    }

}