package com.example.thindie.core.network.dto.dailydto

import com.google.gson.annotations.SerializedName

data class WeatherDailyResponse(
    val daily: Daily,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnits,
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timeZoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
)