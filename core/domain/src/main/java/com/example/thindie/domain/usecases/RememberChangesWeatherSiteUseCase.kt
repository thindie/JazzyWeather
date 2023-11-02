package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject

class RememberChangesWeatherSiteUseCase @Inject constructor(
    private val repositoryDaily: ForecastAbleRepository<WeatherDaily>,
    private val repositoryHourly: ForecastAbleRepository<WeatherHourly>,
) {
    suspend operator fun invoke(forecastAble: ForecastAble) {
        repositoryDaily.rememberChanges(forecastAble)
        repositoryHourly.rememberChanges(forecastAble)
    }
}