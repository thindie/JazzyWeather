package com.example.thindie.core.network.dto

@Suppress("ConstructorParameterNaming")
data class WeatherHourlyDto(
    val latitude: Double,
    val longitude: Double,
    val precipitation: List<Double>,
    val temperatureTwoMeters: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>,
    val windspeedTenMeters: List<Double>,
)