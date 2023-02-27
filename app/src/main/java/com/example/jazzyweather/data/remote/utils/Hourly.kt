package com.example.jazzyweather.data.remote.utils

data class Hourly(
    val temperature_2m: List<Double>,
    val time: List<String>,
)