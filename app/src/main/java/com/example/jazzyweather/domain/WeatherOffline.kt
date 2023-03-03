package com.example.jazzyweather.domain

data class WeatherOffline(
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double,
)