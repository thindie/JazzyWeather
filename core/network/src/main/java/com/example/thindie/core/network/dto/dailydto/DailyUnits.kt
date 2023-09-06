package com.example.thindie.core.network.dto.dailydto

import com.google.gson.annotations.SerializedName

data class DailyUnits(
    @SerializedName("precipitation_hours")
    val precipitationHours: String,
    @SerializedName("precipitation_probability_max")
    val precipitationProbabilityMax: String,
    @SerializedName("precipitation_sum")
    val precipitationSum: String,
    @SerializedName("rain_sum")
    val rainSum: String,
    @SerializedName("showers_sum")
    val showersSum: String,
    @SerializedName("snowfall_sum")
    val snowfallSum: String,
    val time: String,
    @SerializedName("uv_index_max")
    val uvIndexMax: String,
    @SerializedName("winddirection_10m_dominant")
    val windDirection10mDominant: String,
    @SerializedName("windgusts_10m_max")
    val windGusts10mMax: String,
    @SerializedName("windspeed_10m_max")
    val windSpeed10mMax: String
)