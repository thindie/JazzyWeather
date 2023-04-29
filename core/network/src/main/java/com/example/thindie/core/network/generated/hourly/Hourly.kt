package com.example.thindie.core.network.generated.hourly
@Suppress("ConstructorParameterNaming")
internal data class Hourly(
    val precipitation: List<Double>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weathercode: List<Int>,
    val windspeed_10m: List<Double>,
)