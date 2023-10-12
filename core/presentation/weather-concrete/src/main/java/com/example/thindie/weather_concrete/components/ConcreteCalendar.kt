package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.domain.entities.ForecastDay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ConcreteCalendar(
    modifier: Modifier = Modifier,
    digits: List<Int>,
    days: List<ForecastDay>,
    onClickConcreteDay: (Long) -> Unit,
) {

    Row(modifier = modifier.fillMaxWidth()) {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) {
            itemsIndexed(days) { i, forecastDay ->
                Card(
                    onClick = { onClickConcreteDay(forecastDay.inMillis) }
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = forecastDay.weekDay)
                        Text(text = digits[i].toString())
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewConcreteCalendar() {
    JazzyWeatherTheme {

    }
}
