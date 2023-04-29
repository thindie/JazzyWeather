package com.example.thindie.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherDbModel(
    @PrimaryKey
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val temperature: Double,
    val time: String,
    val weatherCode: Int,
    val windDirection: Double,
    val windSpeed: Double,
)

