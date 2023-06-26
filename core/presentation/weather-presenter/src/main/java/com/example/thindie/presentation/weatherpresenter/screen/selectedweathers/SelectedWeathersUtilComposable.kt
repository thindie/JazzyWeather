package com.example.thindie.presentation.weatherpresenter.screen.selectedweathers

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.thindie.presentation.designsystem.textutil.BodyLargeBoldText

@Composable
internal fun <E> List<E>.displayText(index: Int, color: Color, metric: String) {
    this[index].toString().plus(" $metric").BodyLargeBoldText(color = color)
}

@Composable
internal fun windColor(windPower: Double): Color {
    return when (windPower) {
        in 0.0..8.0 -> MaterialTheme.colorScheme.onSurface
        in 8.1..15.1 -> MaterialTheme.colorScheme.onTertiaryContainer
        in 15.1..20.1 -> Color.Magenta
        in 20.1..Double.MAX_VALUE -> Color.Red
        else -> Color.Yellow
    }
}


@Composable
internal fun temperatureColor(temperature: Double): Color {
    return when (temperature) {
        in 0.1..3.0 -> MaterialTheme.colorScheme.error.copy(0.8f)
        in 3.1..9.0 -> MaterialTheme.colorScheme.error
        in 9.1..19.0 -> Color.Red.copy(0.8f)
        in 19.1..Double.MAX_VALUE -> Color.Red
        in -0.1..-3.0 -> MaterialTheme.colorScheme.primary.copy(0.3f)
        in -3.1..-9.0 -> MaterialTheme.colorScheme.primary.copy(0.6f)
        in -9.1..-19.0 -> Color.Blue
        in -19.1..Double.MIN_VALUE -> Color.Blue
        else -> MaterialTheme.colorScheme.onSurface
    }
}

internal fun checkPerticipation(precipitation: List<Double>): String {
    return if (precipitation.any { it > 0.0 }) "ожидаются"
    else "не ожидаются"
}

internal fun checkWind(wind: List<Double>): String {
    return wind.max().toString()
}
