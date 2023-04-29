package com.example.thindie.core.network.generated
@Suppress("ConstructorParameterNaming")
internal data class CurrentWeather(
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double,
)