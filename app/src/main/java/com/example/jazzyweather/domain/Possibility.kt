package com.example.jazzyweather.domain

data class Possibility(
    val place: String,
    val latitude: Float,
    val longitude: Float,
    val timeZone: String,
    val adaptedTimeZone: String,
)