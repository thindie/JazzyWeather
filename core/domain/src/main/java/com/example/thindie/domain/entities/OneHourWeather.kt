package com.example.thindie.domain.entities

data class OneHourWeather(
    val place: String,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timeZoneAbbreviation: String,
    val utcOffsetSeconds: Int,
    val apparentTemperature: Double,
    val precipitation: Double,
    val rain: Double,
    val showers: Double,
    val snowfall: Double,
    val temperature2m: Double,
    val time: String,
    val visibility: Double,
    val weatherCode: Int,
    val windGusts10m: Double,
    val windSpeed10m: Double,
) {
}