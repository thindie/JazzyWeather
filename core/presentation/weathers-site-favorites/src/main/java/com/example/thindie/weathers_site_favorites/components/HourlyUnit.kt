package com.example.thindie.weathers_site_favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.domain.entities.OneHourWeather
import com.example.thindie.presentation.R

@Composable
internal fun HourlyUnit(
    modifier: Modifier = Modifier,
    time: String,
    oneHourWeather: OneHourWeather,
    getDecodedWeatherCode: (Int) -> Int,
) {
    val textStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W800)
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .wrapContentHeight()
            .width(120.dp)
            .clip(MaterialTheme.shapes.small)
            .background(
                brush =
                MaterialTheme.colorScheme.primaryContainer.TransGradientVertical()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = time)

        val isPositive = oneHourWeather.temperature2m > 0.0

        IconTextSection(
            modifier = modifier.padding(horizontal = 12.dp),
            icon = null,
            title = oneHourWeather.temperature2m.toString(),
            color = if (isPositive) Color.Red
            else Color.Blue,
            style = textStyle
        )
        IconTextSection(
            modifier = modifier,
            icon = R.drawable.icon_windy,
            title = oneHourWeather.windSpeed10m.toString(),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        IconTextSection(
            modifier = modifier.padding(vertical = 8.dp),
            icon = getDecodedWeatherCode.invoke(oneHourWeather.weatherCode),
            title = if (oneHourWeather.precipitation > 0.0) oneHourWeather.precipitation.toString() else "",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = textStyle
        )


    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewHourlyUnit() {
    JazzyWeatherTheme {

    }
}
