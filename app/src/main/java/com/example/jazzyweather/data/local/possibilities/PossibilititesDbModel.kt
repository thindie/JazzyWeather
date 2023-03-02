package com.example.jazzyweather.data.local.possibilities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "possibility")
data class PossibilititesDbModel (
    @PrimaryKey
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val timeZone: String,
    val adaptedTimeZone: String,
)