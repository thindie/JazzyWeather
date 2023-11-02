package com.example.thindie.weather_concrete.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.example.thindie.domain.entities.ForecastDay

@Stable
internal class ConcreteCalendarState(val digits: List<Int>, val days: List<ForecastDay>) {
    
}

@Composable
internal fun rememberCalendarState(digits: List<Int>, days: List<ForecastDay>): ConcreteCalendarState {
    return remember(digits[0], days[0]) {
        ConcreteCalendarState(digits = digits, days = days)
    }
}