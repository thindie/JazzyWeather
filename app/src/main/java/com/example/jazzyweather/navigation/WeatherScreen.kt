package com.example.jazzyweather.navigation

internal const val picker = "picker"
internal const val concrete = "concrete"
internal const val all = "all"
internal const val goBack = "back"
enum class WeatherScreen {
    LOCATION_PICKER, CONCRETE, ALL
}

fun WeatherScreen.destination(): String {
    return when (this) {
        WeatherScreen.LOCATION_PICKER -> {
            picker
        }

        WeatherScreen.CONCRETE -> {
            concrete
        }

        WeatherScreen.ALL -> {
            all
        }
    }
}

val destinations = listOf(
    WeatherScreen.CONCRETE, WeatherScreen.LOCATION_PICKER, WeatherScreen.ALL
)