package com.example.jazzyweather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteWeather")
data class WeatherDBModel(
    @PrimaryKey
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Double,
    val windspeed: Double,
)