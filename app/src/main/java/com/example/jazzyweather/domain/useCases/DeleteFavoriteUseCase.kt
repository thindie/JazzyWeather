package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    suspend operator fun invoke(id: String) {
        return jazzy.deleteFromFavorites(id)
    }
}