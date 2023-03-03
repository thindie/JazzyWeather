package com.example.jazzyweather.data.remote.utils.hourly

data class Hourly(
    val precipitation: List<Double>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>,
)