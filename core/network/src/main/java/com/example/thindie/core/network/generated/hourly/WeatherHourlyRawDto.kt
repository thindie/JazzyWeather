package com.example.thindie.core.network.generated.hourly
@Suppress("ConstructorParameterNaming")
internal data class WeatherHourlyRawDto(
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int,
)