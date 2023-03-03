package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.abstractions.Results
import javax.inject.Inject

class GetSavedPossibilitiesUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    suspend operator fun invoke(): Results<List<Possibility>> {
        return jazzy.getSavedPossibilities()
    }
}