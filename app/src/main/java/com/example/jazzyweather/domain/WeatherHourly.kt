package com.example.jazzyweather.domain

data class WeatherHourly(
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val precipitation: List<Double>,
    val temperature2m: List<Double>,
    val time: List<String>,
    val weatherCodes: List<Int>,
    val windSpeed10m: List<Double>,
)