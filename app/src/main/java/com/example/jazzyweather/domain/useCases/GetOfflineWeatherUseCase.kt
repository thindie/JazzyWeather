package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Results
import com.example.jazzyweather.domain.WeatherOffline
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOfflineWeatherUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    operator fun invoke(): Flow<Results<List<WeatherOffline>>> {
        return jazzy.getOfflineWeathers()
    }
}