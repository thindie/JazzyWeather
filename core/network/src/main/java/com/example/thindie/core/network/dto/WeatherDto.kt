package com.example.thindie.core.network.dto

@Suppress("ConstructorParameterNaming")
data class WeatherDto(
    val latitude: Double,
    val longitude: Double,
    val temperature: Double,
    val time: String,
    val weatherCode: Int,
    val windDirection: Double,
    val windSpeed: Double,
    val apparentTemperatureMax: List<Double>,
    val apparentTemperatureMin: List<Double>,
    val precipitationSum: List<Double>,
    val rainSum: List<Double>,
    val showersSum: List<Double>,
    val snowfallSum: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperatureTwoMetersMax: List<Double>,
    val temperatureTwoMetersMin: List<Double>,
    val times: List<String>,
    val weatherCodes: List<Int>,
    val windgustsTenMetersMin: List<Double>,
    val windspeedTenMetersMax: List<Double>,
)