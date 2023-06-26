package com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.drawablemap.toWeatherPresenter

@Suppress("LongParameterList")
@Composable
fun WeatherPrimarySection(
    place: String,
    weatherCode: Int,
    temperature: Double,
    plus: Boolean,
    sunset: List<String>,
    sunrise: List<String>,
    windDirection: Double,
    windSpeed: Double,
    time: String,
    latitude: Float,
    longitude: Float,
    onClick: (String) -> Unit,
) {
    IconButton(
        onClick = { onClick(place) }, modifier = Modifier
            .size(54.dp)
            .padding(start = 8.dp, end = 12.dp, top = 5.dp, bottom = 2.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "",
            Modifier.padding(all = 7.dp)
        )
    }

    TemperatureSection(
        weatherPresenter = weatherCode.toWeatherPresenter(),
        place = place,
        temperature = temperature,
        plus = plus
    )
    AdditionalCurrentWeatherPlaceInfo(
        sunset = sunset.last(),
        sunrise = sunrise.last(),
        windDirection = windDirection,
        windSpeed = windSpeed,
        time = time
    )
}