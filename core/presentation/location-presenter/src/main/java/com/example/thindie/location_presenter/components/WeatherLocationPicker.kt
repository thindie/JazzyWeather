package com.example.thindie.location_presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.presentation.R

@Composable
internal fun WeatherLocationPickerUnit(
    modifier: Modifier = Modifier,
    location: WeatherLocation,
    onClickFavorite: (WeatherLocation) -> Unit,
) {

    Surface(
        modifier = modifier
            .height(80.dp)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = location.city, style = MaterialTheme.typography.titleSmall.copy(
                    textDecoration = TextDecoration.None
                )
            )
            Text(text = location.population)
            Column {
                val textStyle = MaterialTheme.typography.labelSmall
                Text(text = location.latitude, style = textStyle)
                Text(text = location.longitude, style = textStyle)
            }
            IconButton(onClick = { onClickFavorite(location) }) {
                Icon(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.icon_favorite),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherLocationPickerUnit() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        Column {
            WeatherLocationPickerUnit(
                location = WeatherLocation(
                    adminName = "name",
                    capital = "capital",
                    city = "capitalcity",
                    country = "",
                    iso2 = "",
                    latitude = "1234442",
                    longitude = "4323423",
                    population = "12345",
                    populationProper = ""
                ), onClickFavorite = {}, modifier = Modifier
            )
            WeatherLocationPickerUnit(
                location = WeatherLocation(
                    adminName = "name",
                    capital = "capital",
                    city = "capitalcity",
                    country = "",
                    iso2 = "",
                    latitude = "",
                    longitude = "",
                    population = "12345",
                    populationProper = ""
                ), onClickFavorite = {}, modifier = Modifier
            )
        }
    }
}