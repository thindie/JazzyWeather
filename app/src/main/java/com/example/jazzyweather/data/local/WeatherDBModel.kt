package com.example.jazzyweather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteWeather")
data class WeatherDBModel(
    @PrimaryKey
    val place: String,
) {

}