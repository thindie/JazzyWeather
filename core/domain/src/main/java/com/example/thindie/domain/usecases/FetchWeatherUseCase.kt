package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastUpdateRepository
import com.example.thindie.domain.entities.ForecastAble
import javax.inject.Inject

class FetchWeatherUseCase @Inject
constructor(private val repository: ForecastUpdateRepository) {
    suspend operator fun invoke(forecastAble: ForecastAble) {
        repository.fetchSite(forecastAble)
    }

}