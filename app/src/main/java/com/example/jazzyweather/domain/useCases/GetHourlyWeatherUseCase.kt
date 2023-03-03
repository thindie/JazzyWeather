package com.example.jazzyweather.domain.useCases

import com.example.jazzyweather.domain.JazzyWeatherRepository
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.WeatherHourly
import com.example.jazzyweather.domain.abstractions.Results
import javax.inject.Inject

class GetHourlyWeatherUseCase @Inject constructor(private val jazzy: JazzyWeatherRepository) {
    suspend operator fun invoke(possibility: Possibility) : Results<WeatherHourly> {
        return jazzy.getHourlyWeather(possibility)
    }
}