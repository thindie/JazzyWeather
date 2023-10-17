package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.domain.entities.OneHourWeather
import com.example.thindie.presentation.R

@Composable
internal fun HourlyUnit(
    modifier: Modifier = Modifier,
    time: String,
    oneHourWeather: OneHourWeather,
    getDecodedWeatherCode: (Int) -> Int,
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .clip(MaterialTheme.shapes.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = time)

        val isPositive = oneHourWeather.apparentTemperature > 0.0

        IconTextSection(
            modifier = modifier,
            icon = R.drawable.icon_low_temp,
            title = oneHourWeather.apparentTemperature.toString(),
            color = if (isPositive) Color.Red
            else Color.Blue
        )
        IconTextSection(
            modifier = modifier,
            icon = R.drawable.icon_windy,
            title = oneHourWeather.windSpeed10m.toString(),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        IconTextSection(
            modifier = modifier,
            icon = getDecodedWeatherCode.invoke(oneHourWeather.weatherCode),
            title = oneHourWeather.precipitation.toString(),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )


    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewHourlyUnit() {
    JazzyWeatherTheme {

    }
}
