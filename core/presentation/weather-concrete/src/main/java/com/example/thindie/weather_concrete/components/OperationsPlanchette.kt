package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.domain.entities.WeatherDaily
internal typealias Action = OperationsPlanchetteState.Action
@Composable
internal fun OperationsPlanchette(
    modifier: Modifier = Modifier,
    weatherDaily: WeatherDaily,
    onDelete: (WeatherDaily) -> Unit,
    onUpdate: (WeatherDaily) -> Unit,
    onClickBack: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(WeatherConcreteColors.backGroundColors)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
    ) {

    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewOperationsPlanchette() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {

    }
}
