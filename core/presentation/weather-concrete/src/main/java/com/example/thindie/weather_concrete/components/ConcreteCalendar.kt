package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.designsystem.theme.JazzyWeatherTheme


@Composable
internal fun ConcreteCalendar(
    modifier: Modifier = Modifier,
    state: ConcreteCalendarState,
    onClickConcreteDay: (Long) -> Unit,
) {

    Row(modifier = modifier.fillMaxWidth()) {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth(),
            userScrollEnabled = false
        ) {
            itemsIndexed(state.days) { i, forecastDay ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .clickable { onClickConcreteDay(forecastDay.inMillis) }
                ) {
                    Text(text = forecastDay.weekDay)
                    Text(text = state.digits[i].toString())
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
