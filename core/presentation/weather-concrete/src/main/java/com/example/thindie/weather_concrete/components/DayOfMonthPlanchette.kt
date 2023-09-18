package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.utils.TransGradientVertical

@Composable
internal fun DatePlanchette(modifier: Modifier = Modifier, days: List<Int>, currentDay: Int) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .wrapContentHeight()
            .fillMaxWidth(),

        ) {
        LazyRow(
            modifier = modifier
                .border(
                    Dp.Hairline,
                    MaterialTheme
                        .colorScheme
                        .surface
                        .TransGradientVertical(MaterialTheme.colorScheme.onSurfaceVariant),
                    RoundedCornerShape(10.dp)
                )
                .background(MaterialTheme.colorScheme.surface)
                .height(56.dp)
                .fillMaxWidth(),
            userScrollEnabled = false,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(days) {
                Text(
                    text = it.toString(), style = MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        textDecoration = if (it == currentDay) TextDecoration.Underline else TextDecoration.None
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewDatePlanchette() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {

        val list = buildList {
            repeat(7) {
                add(it)
            }
        }
        DatePlanchette(days = list, currentDay = 4)
    }
}