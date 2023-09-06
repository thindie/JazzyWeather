package com.example.thindie.core.network.dto.hourlydto

import com.google.gson.annotations.SerializedName

data class HourlyUnits(
    @SerializedName("apparent_temperature")
    val apparentTemperature: String,
    val precipitation: String,
    val rain: String,
    val showers: String,
    val snowfall: String,
    @SerializedName("temperature_2m")
    val temperature2m: String,
    val time: String,
    val visibility: String,
    @SerializedName("weathercode")
    val weatherCode: String,
    @SerializedName("windgusts_10m")
    val windGusts10m: String,
    @SerializedName("windspeed_10m")
    val windSpeed10m: String,
)