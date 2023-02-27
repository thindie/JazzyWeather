package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Weather
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    suspend operator fun invoke(weather: Weather) {
        return jazzy.addToFavorites(weather)
    }
}