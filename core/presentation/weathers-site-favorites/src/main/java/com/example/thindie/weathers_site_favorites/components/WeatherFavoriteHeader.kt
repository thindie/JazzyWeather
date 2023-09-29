package com.example.thindie.weathers_site_favorites.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse

@Composable
internal fun WeatherFavoriteHeader(
    modifier: Modifier = Modifier,
    city: String,
    celsium: String,
    @StringRes weatherCurrent: Int,
    @DrawableRes weatherCurrentPic: Int,
    contextDependableSurfaceColor: Brush,
    onClickHeader: () -> Unit,
) {
    Column(
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
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            LazyRow(modifier = modifier.fillMaxWidth(0.6f), content = {
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .clickable { onClickHeader() },
                        text = city,
                        style = MaterialTheme
                            .typography
                            .headlineLarge
                            .copy(Color.White)
                    )
                }
            })

            IconTextSection(
                modifier = modifier
                    .wrapContentSize(align = Alignment.Center),
                icon = com.example.thindie.presentation.R.drawable.icon_celsius,
                title = celsium,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )

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
            WeatherFavoriteHeader(city = "Kaliningrad",
                celsium = "24",
                contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.surface.TransGradientVertical()
                } else {
                    MaterialTheme.colorScheme.primary.TransGradientVerticalInverse()
                },
                weatherCurrent = 0,
                weatherCurrentPic = 0,
                onClickHeader = {})
            WeatherFavoriteHourlyUnit(
                time = "14:00",
                celsium = 25.00,
                windSpeed = "wind 4 m/s",
                precipitation = 0.3,
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
            WeatherFavoriteHeader(city = "Kaliningrad", celsium = "24",

                contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.onPrimary.TransGradientVerticalInverse()
                } else {
                    MaterialTheme.colorScheme.surface.TransGradientVertical()
                }, weatherCurrent = 0, weatherCurrentPic = 0, onClickHeader = {}

            )
            WeatherFavoriteHourlyUnit(
                time = "14:00",
                celsium = 24.00,
                windSpeed = "wind 4 m/s",
                precipitation = 0.3,
                contextDependableSurfaceColor = if (isSystemInDarkTheme()) {
                    MaterialTheme.colorScheme.onPrimary.TransGradientVertical()
                } else {
                    MaterialTheme.colorScheme.surface.TransGradientVerticalInverse()
                }
            )
        }

    }
}