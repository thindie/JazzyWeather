package com.example.thindie.domain.usecases

import android.util.Log
import com.example.thindie.domain.ForecastAbleRepository
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull

class ObserveDailyWeatherUseCase @Inject
constructor(private val repository: ForecastAbleRepository<WeatherDaily>) {
    operator fun invoke(forecastAbleEvent: Flow<ForecastAble>): Flow<WeatherDaily> {
        return repository.observeForecastAbleList()
            .combine(forecastAbleEvent) { source, forecastAble ->
                Log.d("SERVICE_TAG_USE_CASE", forecastAble.getSight())
                source.firstOrNull { it.place == forecastAble.getSight() }
            }
            .filterNotNull()
    }
}