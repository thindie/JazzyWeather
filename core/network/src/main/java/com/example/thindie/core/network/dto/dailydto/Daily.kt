package com.example.thindie.core.network.dto.dailydto

import com.google.gson.annotations.SerializedName

data class Daily(
    @SerializedName("precipitation_hours")
    val precipitationHours: List<Double>,
    @SerializedName("precipitation_probability_max")
    val precipitationProbabilityMax: List<Int>,
    @SerializedName("precipitation_sum")
    val precipitationSum: List<Double>,
    @SerializedName("rain_sum")
    val rainSum: List<Double>,
    @SerializedName("showers_sum")
    val showersSum: List<Double>,
    @SerializedName("snowfall_sum")
    val snowfallSum: List<Double>,
    val time: List<String>,
    @SerializedName("uv_index_max")
    val uvIndexMax: List<Double>,
    @SerializedName("winddirection_10m_dominant")
    val windDirection10mDominant: List<Int>,
    @SerializedName("windgusts_10m_max")
    val windGusts10mMax: List<Double>,
    @SerializedName("windspeed_10m_max")
    val windSpeed10mMax: List<Double>,
)