package com.example.jazzyweather.ui



data class WeatherHourlyUIModel(
    val place: String,
    val latitude: Float,
    val longitude: Float,
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
    val isPlus: Boolean = temperature > 0.00,
    val precipitation: List<Double>,
    val temperature_2m: List<Double>,
    val time_hourly: List<String>,
    val weathercode_hourly: List<Int>,
    val windspeed_10m_hourly: List<Double>,
)