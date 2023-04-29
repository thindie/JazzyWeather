package com.example.thindie.core.network.generated

import com.example.thindie.core.network.dto.WeatherDto
import com.example.thindie.core.network.dto.WeatherDtoBuilder

@Suppress("ConstructorParameterNaming")
data class WeatherRawDto internal constructor(
    internal val current_weather: CurrentWeather,
    internal val daily: Daily,
    internal val daily_units: DailyUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    internal val hourly: Hourly,
    internal val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int,
)
