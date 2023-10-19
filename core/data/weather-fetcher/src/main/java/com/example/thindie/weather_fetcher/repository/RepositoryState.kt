package com.example.thindie.weather_fetcher.repository

import com.example.thindie.domain.entities.ForecastAble

data class RepositoryState(
    val forecastAble: ForecastAble?,
    val foreCastAbleList: List<ForecastAble>,
)