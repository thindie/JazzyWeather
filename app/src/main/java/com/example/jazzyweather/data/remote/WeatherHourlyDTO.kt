package com.example.jazzyweather.data.remote

data class WeatherHourlyDTO(
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val precipitation: List<Double>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>,
)