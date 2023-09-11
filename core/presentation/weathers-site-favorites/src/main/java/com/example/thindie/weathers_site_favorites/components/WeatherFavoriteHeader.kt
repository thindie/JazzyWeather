package com.example.thindie.weathers_site_favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse

@Composable
internal fun WeatherFavoriteHeader(
    modifier: Modifier = Modifier,
    city: String,
    celsium: String,
    sunrise: String,
    sunset: String,
    precipitation: String,
    contextDependableSurfaceColor: Brush,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .height(120.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(contextDependableSurfaceColor)
                .padding(horizontal = 8.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(0.7F)
                    .fillMaxHeight()
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = city,
                    style = MaterialTheme.typography.headlineLarge.copy(MaterialTheme.colorScheme.onSurfaceVariant)
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = sunrise,
                        style = MaterialTheme.typography.labelSmall.copy(MaterialTheme.colorScheme.onPrimary)
                    )
                    Text(
                        text = sunset,
                        style = MaterialTheme.typography.labelSmall.copy(MaterialTheme.colorScheme.onPrimary)
                    )
                }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = celsium,
                    style = MaterialTheme.typography.headlineLarge.copy(Color.White)
                )
                Text(
                    text = precipitation,
                    style = MaterialTheme.typography.titleMedium.copy(MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoriteHeader() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            WeatherFavoriteHeader(
                city = "Kaliningrad",
                celsium = "24",
                sunrise = "sunrise 06:32",
                sunset = "sunset 19:32",
                precipitation = "clear",
                contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.surface.TransGradientVertical()
                } else {
                    MaterialTheme.colorScheme.primary.TransGradientVerticalInverse()
                }
            )
            WeatherFavoriteHourlyUnit(
                time = "14:00",
                celsium = "24",
                windSpeed = "wind 4 m/s",
                precipitation = "clear",
                contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.surface.TransGradientVerticalInverse()
                } else {
                    MaterialTheme.colorScheme.primary.TransGradientVertical()
                }
            )
        }

    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoriteHeaderDark() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme(true) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            WeatherFavoriteHeader(
                city = "Kaliningrad",
                celsium = "24",
                sunrise = "sunrise 06:32",
                sunset = "sunset 19:32",
                precipitation = "clear",
                contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.onPrimary.TransGradientVerticalInverse()
                } else {
                    MaterialTheme.colorScheme.surface.TransGradientVertical()
                }

            )
            WeatherFavoriteHourlyUnit(
                time = "14:00",
                celsium = "24",
                windSpeed = "wind 4 m/s",
                precipitation = "clear",
                contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.onPrimary.TransGradientVertical()
                } else {
                    MaterialTheme.colorScheme.surface.TransGradientVerticalInverse()
                }
            )
        }

    }
}