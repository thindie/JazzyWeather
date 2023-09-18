package com.example.thindie.weather_concrete.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.thindie.presentation.R

@Composable
internal fun WeatherConcreteTitle(
    modifier: Modifier = Modifier,
    city: String,
    timeSunset: String,
    timeSunrise: String,
    timeZone: String,
    latitude: Float,
    longitude: Float,
    elevation: Float,
) {
    val shouldShowDialog = remember { mutableStateOf(false) }

    if (shouldShowDialog.value) {
        Dialog(onDismissRequest = { shouldShowDialog.value = false }) {
            WeatherConcreteDialogContent(
                latitude = latitude,
                longitude = longitude,
                elevation = elevation.toDouble()
            )
        }
    }

    Column(
        modifier = modifier
            .height(155.dp)
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(WeatherConcreteColors.titleColors),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyRow(modifier = modifier.padding(horizontal = 20.dp)) {
                item {
                    Text(
                        text = city,
                        style = MaterialTheme
                            .typography
                            .headlineLarge
                            .copy(MaterialTheme.colorScheme.onSurface)
                    )
                }

            }

        }
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            IconTextSection(modifier = modifier, icon = R.drawable.icon_sunny, title = timeSunrise)

            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_moon_cresent,
                title = timeSunset
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_time_outline,
                title = timeZone
            )
            IconButton(onClick = { shouldShowDialog.value = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_information),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherConcreteTitle() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        Column(
            Modifier
                .background(WeatherConcreteColors.backGroundColors)
                .fillMaxSize()
        ) {
            WeatherConcreteTitle(
                city = "Санкт-Петербург",
                timeSunset = "333",
                timeSunrise = "444",
                timeZone = "EET", latitude = 0.2f, longitude = 0.3f, elevation = 0.1f,

                )
            DatePlanchette(days = listOf(12, 23, 62, 73, 48, 64, 25), currentDay = 5)
            LazyColumn() {
                item {
                    WeatherNamedGraph(
                        graphValues = listOf(1.0, 2.0, -8.9, 4.5, 2.0, 8.9, 4.5),
                        titlePic = R.drawable.icon_celsius,
                        positiveColor = WeatherConcreteColors.positiveTemperature,
                        negativeColor = WeatherConcreteColors.negativeTemperature
                    )
                }
                item {
                    WeatherNamedGraph(
                        graphValues = listOf(1.0, 2.0, 8.9, 4.5, 17.4, 2.4, 2.4),
                        titlePic = R.drawable.icon_windy,
                        positiveColor = WeatherConcreteColors.windValue,
                        negativeColor = WeatherConcreteColors.windValue,
                        animationTime = 800
                    )
                }
                item {
                    WeatherNamedGraph(
                        graphValues = listOf(0.0, 2.0, 8.9, 4.5, 0.2, 2.4, 1.1),
                        titlePic = R.drawable.icon_water_drop,
                        positiveColor = WeatherConcreteColors.rainValue,
                        negativeColor = WeatherConcreteColors.rainValue,
                        animationTime = 1200
                    )
                }


            }


        }

    }
}

@Composable
private fun IconTextSection(
    modifier: Modifier,
    @DrawableRes icon: Int,
    title: String,
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier.padding(horizontal = 8.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onTertiary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(
                MaterialTheme.colorScheme.onTertiary
            )
        )
    }
}
