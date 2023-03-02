package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Weather
import javax.inject.Inject

class SavePossibilitiesUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    suspend operator fun invoke(possibilities: List<Possibility>) {
        return jazzy.savePossibilities(possibilities)
    }
}