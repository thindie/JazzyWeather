package com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.widescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.R
import com.example.thindie.presentation.designsystem.textutil.BodyLargeText
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.util.TimeParser
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.checkPerticipation
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.checkWind

@Suppress("LongParameterList")
@Composable
fun WeatherInformationSection(
    temperature: Double,
    windspeed: Double,
    sunrise: List<String>,
    sunset: List<String>,
    place: String,
    latitude: Float,
    longitude: Float,
    plus: Boolean,
    modifier: Modifier = Modifier,
    precipitation: List<Double>,
    wind: List<Double>,
    onSelectedDestination: (String, Float, Float) -> Unit,
    onChangePinnedStatus: (String) -> Unit,

    ) {
    Card(modifier.fillMaxWidth(), shape = ShapeDefaults.ExtraLarge) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(80.dp)
                .padding(
                    start = 25.dp, end = 22.dp, top = 4.dp, bottom = 4.dp
                )
        ) {
            stringResource(id = R.string.place_short, place).HeadLineText(modifier =
            Modifier.clickable {
                onSelectedDestination(place, latitude, longitude)
            })
            stringResource(id = R.string.temperature_short, temperature.toString())
                .HeadLineText(
                    color = if (plus) Color.Red else Color.Blue
                )
            stringResource(id = R.string.precipit_short, checkPerticipation(precipitation))
                .BodyLargeText()
            stringResource(id = R.string.wind_short, checkWind(wind)).BodyLargeText()
            stringResource(id = R.string.sunrise).BodyLargeText()
            TimeParser(sunrise.first()).time.BodyLargeText()
            stringResource(id = R.string.sunset).BodyLargeText()
            TimeParser(sunset.first()).time.BodyLargeText()
            IconButton(onClick = { onChangePinnedStatus(place) }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
            }
        }
    }
}