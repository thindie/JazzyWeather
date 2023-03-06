package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.domain.abstractions.Results
import javax.inject.Inject

class GetFavoriteLocationsUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    suspend operator fun invoke(): Results<List<Weather>> {
        return jazzy.getFavoriteWeatherLocations()
    }
}