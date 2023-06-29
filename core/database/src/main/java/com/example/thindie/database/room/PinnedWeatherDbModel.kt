package com.example.thindie.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedWeather")
data class PinnedWeatherDbModel(
    @PrimaryKey
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double,
    val apparentTemperatureMax: List<Double>,
    val apparentTemperatureMin: List<Double>,
    val precipitationSum: List<Double>,
    val rainSum: List<Double>,
    val showersSum: List<Double>,
    val snowfallSum: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperatureMaxHourly: List<Double>,
    val temperatureMinHourly: List<Double>,
    val timesHourly: List<String>,
    val weatherCodesHourly: List<Int>,
    val windgusts10mMaxHourly: List<Double>,
    val windspeed10mMaxHourly: List<Double>,
    val isPlus: Boolean = temperature > 0.00,
)