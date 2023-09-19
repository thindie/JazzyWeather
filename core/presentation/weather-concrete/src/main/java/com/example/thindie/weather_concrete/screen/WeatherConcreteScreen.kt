package com.example.thindie.weather_concrete.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.presentation.R
import com.example.thindie.weather_concrete.components.DatePlanchette
import com.example.thindie.weather_concrete.components.WeatherConcreteColors
import com.example.thindie.weather_concrete.components.WeatherConcreteTitle
import com.example.thindie.weather_concrete.components.WeatherNamedGraph
import com.example.thindie.weather_concrete.viewmodel.WeatherConcreteViewModel

@Composable
internal fun WeatherConcreteScreen(
    modifier: Modifier = Modifier,
    screenState: WeatherConcreteViewModel.ConcreteWeatherScreenState,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WeatherConcreteColors.backGroundColors),
    ) {

        with(screenState) {
            if (weatherDaily != null) {
                Spacer(modifier = modifier.height(20.dp))
                WeatherConcreteTitle(
                    city = weatherDaily.place,
                    timeSunset = sunset,
                    timeSunrise = sunrise,
                    timeZone = weatherDaily.timezone,
                    latitude = weatherDaily.latitude,
                    longitude = weatherDaily.longitude,
                    elevation = weatherDaily.elevation,
                )
                DatePlanchette(days = weekDays, currentDay = currentDay)
                LazyColumn() {
                    item {
                        WeatherNamedGraph(
                            graphValues = weatherDaily.apparentTemperatureMin,
                            titlePic = R.drawable.icon_celsius,
                            positiveColor = WeatherConcreteColors.positiveTemperature,
                            negativeColor = WeatherConcreteColors.negativeTemperature
                        )
                    }
                    item {
                        WeatherNamedGraph(
                            graphValues = weatherDaily.windSpeed10mMax,
                            titlePic = R.drawable.icon_windy,
                            positiveColor = WeatherConcreteColors.windValue,
                            negativeColor = WeatherConcreteColors.windValue,
                            animationTime = 800
                        )
                    }
                    item {
                        WeatherNamedGraph(
                            graphValues = weatherDaily.precipitationSum,
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
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoritesScreen() {
    JazzyWeatherTheme {

    }
}

