package com.example.thindie.core.network.dto

@Suppress("ConstructorParameterNaming")
data class WeatherDailyDto(
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    val timezone: String,
    val timeZoneAbbreviation: String,
    val utcOffsetSeconds: Int,
    val precipitationHours: List<Double>,
    val precipitationProbabilityMax: List<Int>,
    val precipitationSum: List<Double>,
    val rainSum: List<Double>,
    val showersSum: List<Double>,
    val snowfallSum: List<Double>,
    val time: List<String>,
    val uvIndexMax: List<Double>,
    val windDirection10mDominant: List<Int>,
    val windGusts10mMax: List<Double>,
    val windSpeed10mMax: List<Double>,
)