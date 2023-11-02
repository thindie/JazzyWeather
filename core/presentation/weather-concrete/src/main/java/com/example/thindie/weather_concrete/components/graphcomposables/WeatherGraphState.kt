package com.example.thindie.weather_concrete.components.graphcomposables

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Stable
class WeatherGraphState(
    @DrawableRes val graphIcon: Int,
    val iconTint: Color,
    val list: List<Number>,
    val firstColorComponent: Color,
    val secondColorComponent: Color,
)

@Composable
fun rememberWeatherGraphState(
    @DrawableRes graphIcon: Int,
    iconTint: Color,
    list: List<Number>,
    firstColorComponent: Color,
    secondColorComponent: Color,
): WeatherGraphState {
    return remember(list[0], list[2], list[4]) {

        WeatherGraphState(
            graphIcon = graphIcon,
            iconTint = iconTint,
            list = list,
            firstColorComponent = firstColorComponent,
            secondColorComponent = secondColorComponent
        )
    }
}