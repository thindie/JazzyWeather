package com.example.thindie.domain.entities

data class OneDayWeather(
    val place: String,
    val latitude: Double,
    val longitude: Double,
    val elevation: Double,
    val timezone: String,
    val timeZoneAbbreviation: String,
    val utcOffsetSeconds: Int,
    val precipitationHours: Double,
    val precipitationProbabilityMax: Int,
    val precipitationSum: Double,
    val rainSum: Double,
    val showersSum: Double,
    val snowfallSum: Double,
    val time: String,
    val uvIndexMax: Double,
    val windDirection10mDominant: Int,
    val windGusts10mMax: Double,
    val windSpeed10mMax: Double,
)