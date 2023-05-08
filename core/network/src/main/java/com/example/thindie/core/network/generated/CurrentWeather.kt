package com.example.thindie.core.network.generated

data class CurrentWeather(
    val is_day: Int,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double
)