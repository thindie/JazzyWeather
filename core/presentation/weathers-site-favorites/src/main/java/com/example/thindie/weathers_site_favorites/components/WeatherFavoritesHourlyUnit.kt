package com.example.thindie.weathers_site_favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse
import com.example.thindie.presentation.R

@Composable
internal fun WeatherFavoriteHourlyUnit(
    modifier: Modifier = Modifier,
    contextDependableSurfaceColor: Brush,
    time: String,
    celsium: Double,
    windSpeed: String,
    precipitation: Double,
    isFalls: Boolean = precipitation != 0.0,
    isSnowy: Boolean = celsium < 0.5,
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(21.dp))
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = modifier
                .background(
                    contextDependableSurfaceColor
                )
                .padding(all = 15.dp)
                .wrapContentWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = time,
                style = MaterialTheme.typography.titleLarge.copy(
                    MaterialTheme.colorScheme.onPrimary
                )
            )
            Text(
                text = celsium.toString(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = WeatherFavoritesColors.temperatureColor(temperature = celsium),
                    fontWeight = FontWeight.W700
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = windSpeed,
                    style = MaterialTheme.typography.labelMedium.copy(
                        WeatherFavoritesColors.windIndicationsColor()
                    )
                )
                Icon(
                    painter = painterResource(id = R.drawable.icon_windy),
                    tint = WeatherFavoritesColors.windIndicationsColor(),
                    contentDescription = null
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isFalls) {
                    Text(
                        text = precipitation.toString(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            MaterialTheme.colorScheme.onPrimary
                        )
                    )
                    Icon(
                        painter = painterResource(id = if (isSnowy) R.drawable.icon_snowflake else R.drawable.icon_water_drop),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null
                    )
                } else
                    Icon(
                        painter = painterResource(id = R.drawable.icon_sunny),
                        tint = WeatherFavoritesColors.precipitationClearColor(),
                        contentDescription = null
                    )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoriteHourlyUnit() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        WeatherFavoriteHourlyUnit(
            time = "14:00",
            celsium = 5.00,
            windSpeed = "ветер 4 м/с",
            precipitation = 0.2,
            contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                MaterialTheme.colorScheme.primary.TransGradientVertical()
            } else {
                MaterialTheme.colorScheme.surface.TransGradientVerticalInverse()
            }
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoriteHourlyUnitDark() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme(true) {
        WeatherFavoriteHourlyUnit(
            time = "14:00",
            celsium = 5.00,
            windSpeed = "ветер 4 м/с",
            precipitation = 0.3,
            contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                MaterialTheme.colorScheme.primary.TransGradientVertical()
            } else {
                MaterialTheme.colorScheme.surface.TransGradientVerticalInverse()
            }
        )
    }
}