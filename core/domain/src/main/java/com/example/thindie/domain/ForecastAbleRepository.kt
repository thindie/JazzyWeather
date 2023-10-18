package com.example.thindie.domain

import com.example.thindie.domain.entities.ForecastAble
import kotlinx.coroutines.flow.Flow

interface  ForecastAbleRepository <T: ForecastAble> {
    suspend fun requestForecastAble(forecastAble: ForecastAble)

    suspend fun requestConcreteDateForecastAble(
        simpleIso8106: String,
        forecastAble: ForecastAble,
    )


    fun observeForecastAbleList(): Flow<List<T>>

    fun observeForecastAble(): Flow<T>

    suspend fun deleteForecastAble(place: String)

    suspend fun rememberWeatherLocation(forecastAble: ForecastAble)

}