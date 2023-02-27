package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchLocationUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    operator fun invoke(location: String): Flow<Results<List<Possibility>>> {
        return jazzy.searchAndSelectLocation(location)
    }
}