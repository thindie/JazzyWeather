package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.R

@Composable
internal fun TitleInformation(
    modifier: Modifier = Modifier,
    latitude: Double,
    longitude: Double,
    elevation: Double,
) {
    Column {
        val currentColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f)

        Row(
            modifier = modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_celsius,
                title = stringResource(id = R.string.text_temperature_representation),
                color = currentColor
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_windy,
                title = stringResource(id = R.string.text_wind_representation),
                color = currentColor

            )

            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_water_drop,
                title = stringResource(id = R.string.text_temperature_precipitation_representation),
                color = currentColor
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_ultraviolet,
                title = stringResource(id = R.string.text_ultraviolet_index),
                color = currentColor

            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Row(
            modifier = modifier
                .height(13.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {

            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_latitude,
                title = latitude.toString(),
                color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.3f)
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_longitude,
                title = longitude.toString(),
                color = currentColor.copy(alpha = 0.5f)

            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_elevation,
                title = elevation.toString(),
                color = currentColor.copy(alpha = 0.5f)

            )

        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherConcreteDialogContent() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        TitleInformation(latitude = 22.0, longitude = 33.0, elevation = 1500.00)
    }
}
