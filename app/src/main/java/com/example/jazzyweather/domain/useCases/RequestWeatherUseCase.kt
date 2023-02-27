package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Results
import com.example.jazzyweather.domain.Weather
import javax.inject.Inject

class RequestWeatherUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    suspend operator fun invoke(possibility: Possibility): Results<Weather> {
        return jazzy.requestWeather(possibility)
    }
}