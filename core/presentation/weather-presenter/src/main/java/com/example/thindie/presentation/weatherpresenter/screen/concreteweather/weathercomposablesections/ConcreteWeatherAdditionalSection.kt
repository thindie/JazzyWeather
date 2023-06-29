package com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.R
import com.example.thindie.presentation.designsystem.textutil.BodyLargeBoldText
import com.example.thindie.presentation.designsystem.textutil.BodyLargeText
import com.example.thindie.presentation.util.TimeParser

@Composable
fun AdditionalCurrentWeatherPlaceInfo(
    modifier: Modifier = Modifier,
    sunset: String,
    sunrise: String,
    windDirection: Double,
    windSpeed: Double,
    time: String,
) {
    val values = PaddingValues(all = 5.dp)
    Card(
        shape = ShapeDefaults.ExtraLarge,
        modifier = modifier
            .padding(values)
            .width(400.dp)
            .height(100.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                    stringResource(
                        id = R.string.sunrise, ""
                    ).BodyLargeText()
                    TimeParser(sunrise).time.BodyLargeBoldText()


                    stringResource(
                        id = R.string.sunset, ""
                    ).BodyLargeText()
                    TimeParser(sunset).time.BodyLargeBoldText()
            }
            Column(modifier = modifier.padding(all = 10.dp)) {
                    stringResource(
                        id = R.string.wind_speed,
                        windSpeed.toString()
                    ).BodyLargeBoldText()
                Row {
                    stringResource(
                        id = R.string.time_on
                    )
                        .BodyLargeText()
                    TimeParser(time).time.BodyLargeText()
                }

            }

        }
    }
}

