package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thindie.presentation.R

@Composable
internal fun WeatherConcreteDialogContent(
    modifier: Modifier = Modifier,
    latitude: Float,
    longitude: Float,
    elevation: Double,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(WeatherConcreteColors.titleColors)
            .height(340.dp)
            .width(250.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier
                .padding(all = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.text_information),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.headlineLarge.copy(MaterialTheme.colorScheme.onSurface)
                )
            }

            val textStyle = MaterialTheme.typography.labelSmall.copy(
                MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                textAlign = TextAlign.Justify,
            )
            Text(
                text = stringResource(id = R.string.text_temperature_representation),
                style = textStyle
                    .copy(
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.5f)
                    )
            )
            Text(
                text = stringResource(id = R.string.text_wind_representation),
                style = textStyle.copy(
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.6f)
                )
            )
            Text(
                text = stringResource(id = R.string.text_temperature_precipitation_representation),
                style = textStyle.copy(
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.onTertiary.copy(alpha = 0.7f)
                )
            )
            Row {
                Text(
                    text = stringResource(
                        id = R.string.text_information_location_values,
                        latitude.toString(),
                        longitude.toString(),
                        elevation.toString()
                    ),
                    style = textStyle.copy(
                        fontSize = 16.sp,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.W200
                    )
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherConcreteDialogContent() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        WeatherConcreteDialogContent(latitude = 22f, longitude = 33f, elevation = 1500.00)
    }
}
