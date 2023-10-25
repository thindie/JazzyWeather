package com.example.thindie.domain

import com.example.thindie.domain.entities.ForecastAble
import kotlinx.coroutines.flow.Flow

interface  ForecastAbleRepository <T: ForecastAble> {

    fun observeForecastAbleList(): Flow<List<T>>

    suspend fun deleteForecastAble(place: String)

    suspend fun rememberChanges(forecastAble: ForecastAble)

}