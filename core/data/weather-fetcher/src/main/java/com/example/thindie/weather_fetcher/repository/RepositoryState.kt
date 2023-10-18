package com.example.thindie.weather_fetcher.repository

import com.example.thindie.domain.entities.ForecastAble

data class RepositoryState(
    val isFetchAllowed: Boolean,
    val isNetworkAvailAble: Boolean,
    val forecastAble: ForecastAble?,
    val foreCastAbleList: List<ForecastAble>,
)