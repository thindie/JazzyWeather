package com.example.thindie.domain.usecases

import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.domain.entities.WeatherHourly
import javax.inject.Inject
import javax.inject.Named

class DeleteWeatherSiteUseCase @Inject constructor(
     private val repositoryDaily: ForecastAbleRepository<WeatherDaily>,
     private val repositoryHourly: ForecastAbleRepository<WeatherHourly>,
) {
    suspend operator fun invoke(place: String) {
        repositoryDaily.deleteForecastAble(place)
        repositoryHourly.deleteForecastAble(place)
    }

}