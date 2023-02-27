package com.example.jazzyweather.data.remote

data class WeatherDTO(
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double,
    val apparent_temperature_max: List<Double>,
    val apparent_temperature_min: List<Double>,
    val precipitation_sum: List<Double>,
    val rain_sum: List<Double>,
    val showers_sum: List<Double>,
    val snowfall_sum: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val times: List<String>,
    val weathercodes: List<Int>,
    val windgusts_10m_max: List<Double>,
    val windspeed_10m_max: List<Double>,
)