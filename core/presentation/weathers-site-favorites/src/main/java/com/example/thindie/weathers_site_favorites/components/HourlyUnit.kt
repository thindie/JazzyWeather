package com.example.thindie.weathers_site_favorites.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
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
            .width(120.dp)
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = time, style = MaterialTheme.typography.titleLarge)
        }
        val isPositive = oneHourWeather.apparentTemperature > 0.0
        val isClear = oneHourWeather.precipitation == 0.0

        val textStyle = MaterialTheme.typography.bodyLarge
        Spacer(modifier = modifier.height(20.dp))
        IconTextSection(
            modifier = modifier,
            icon = R.drawable.icon_low_temp,
            title = oneHourWeather.apparentTemperature.toString(),
            color = if (isPositive) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.primary,
            style = textStyle
        )
        Spacer(modifier = modifier.height(20.dp))
        IconTextSection(
            modifier = modifier,
            icon = R.drawable.icon_windy,
            title = oneHourWeather.windSpeed10m.toString(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = textStyle
        )
        Spacer(modifier = modifier.height(20.dp))
        IconTextSection(
            modifier = modifier,
            icon = getDecodedWeatherCode.invoke(oneHourWeather.weatherCode),
            title = oneHourWeather.precipitation.toString(),
            color = if (isClear.not()) MaterialTheme.colorScheme.inversePrimary
            else MaterialTheme.colorScheme.onPrimaryContainer,
            style = textStyle
        )
        Spacer(modifier = modifier.height(20.dp))

    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewHourlyUnit() {
    JazzyWeatherTheme {
        HourlyUnit(time = "19:00", oneHourWeather = OneHourWeather(
            place = "",
            latitude = 0.0,
            longitude = 0.0,
            timezone = "",
            timeZoneAbbreviation = "",
            utcOffsetSeconds = 0,
            apparentTemperature = 0.0,
            precipitation = 0.0,
            rain = 0.0,
            showers = 0.0,
            snowfall = 0.0,
            temperature2m = 0.0,
            time = "",
            visibility = 0.0,
            weatherCode = 0,
            windGusts10m = 0.0,
            windSpeed10m = 0.0
        ), getDecodedWeatherCode = {
            R.drawable.icon_low_temp
        })
    }
}
