package com.example.thindie.weather_concrete.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse


@Composable
internal fun ConcreteCalendar(
    modifier: Modifier = Modifier,
    state: ConcreteCalendarState,
    onClickConcreteDay: (Long) -> Unit,
) {
    val currentClickedElement = rememberSaveable {
        mutableStateOf<Int?>(null)
    }

    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(brush = MaterialTheme.colorScheme.primaryContainer.TransGradientVerticalInverse())
            .fillMaxWidth()
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            userScrollEnabled = false
        ) {
            itemsIndexed(state.days) { i, forecastDay ->

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .clip(MaterialTheme.shapes.large)
                        .background(
                            brush =
                            if (currentClickedElement.value == i)
                                MaterialTheme.colorScheme.primaryContainer.TransGradientVertical()
                            else Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent
                                )
                            )
                        )
                        .clickable {
                            if (currentClickedElement.value == i) {
                                currentClickedElement.value = null
                            } else {
                                currentClickedElement.value = i
                            }
                            onClickConcreteDay(forecastDay.inMillis)
                        }
                ) {
                    Text(text = forecastDay.weekDay, modifier = Modifier.padding(all = 4.dp))
                    Text(text = state.digits[i].toString())
                    AnimatedVisibility(
                        visible = currentClickedElement.value != null
                                && currentClickedElement.value == i
                    ) {
                        if (currentClickedElement.value !== null) {
                            Text(text = ".", style = MaterialTheme.typography.headlineMedium)
                        }
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
