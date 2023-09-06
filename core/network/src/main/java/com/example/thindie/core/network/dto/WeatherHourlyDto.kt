package com.example.thindie.core.network.dto

data class WeatherHourlyDto(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timeZoneAbbreviation: String,
    val utcOffsetSeconds: Int,
    val apparentTemperature: List<Double>,
    val precipitation: List<Double>,
    val rain: List<Double>,
    val showers: List<Double>,
    val snowfall: List<Double>,
    val temperature2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    val weatherCode: List<Int>,
    val windGusts10m: List<Double>,
    val windSpeed10m: List<Double>,
)