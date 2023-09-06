package com.example.thindie.core.network.dto.hourlydto

import com.google.gson.annotations.SerializedName

data class WeatherHourlyResponse(
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    val hourly: Hourly,
    @SerializedName("hourly_units")
    val hourlyUnits: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timeZoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
)