package com.example.thindie.core.network.dto.hourlydto

import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("apparent_temperature")
    val apparentTemperature: List<Double>,
    val precipitation: List<Double>,
    val rain: List<Double>,
    val showers: List<Double>,
    val snowfall: List<Double>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    @SerializedName("weathercode")
    val weatherCode: List<Int>,
    @SerializedName("windgusts_10m")
    val windGusts10m: List<Double>,
    @SerializedName("windspeed_10m")
    val windSpeed10m: List<Double>
)