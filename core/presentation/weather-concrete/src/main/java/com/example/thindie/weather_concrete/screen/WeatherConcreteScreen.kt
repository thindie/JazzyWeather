package com.example.thindie.weather_concrete.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.AnimatedContent
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.presentation.R
import com.example.thindie.weather_concrete.components.DatePlanchette
import com.example.thindie.weather_concrete.components.WeatherConcreteColors
import com.example.thindie.weather_concrete.components.WeatherConcreteTitle
import com.example.thindie.weather_concrete.components.WeatherNamedGraph
import com.example.thindie.weather_concrete.components.graphcomposables.WeatherTemperatureGraphHigh
import com.example.thindie.weather_concrete.components.graphcomposables.WeatherTemperatureGraphLow
import com.example.thindie.weather_concrete.viewmodel.WeatherConcreteViewModel

@Composable
internal fun WeatherConcreteScreen(
    modifier: Modifier = Modifier,
    screenState: WeatherConcreteViewModel.ConcreteWeatherScreenState,
    onEdit: (String) -> Unit,
    onRemove: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WeatherConcreteColors.backGroundColors),
    ) {

        with(screenState) {
            if (weatherDaily != null ) {
                Spacer(modifier = modifier.height(20.dp))
                WeatherConcreteTitle(
                    city = weatherDaily.place,
                    timeSunset = sunset,
                    timeSunrise = sunrise,
                    timeZone = weatherDaily.timezone,
                    latitude = weatherDaily.latitude,
                    longitude = weatherDaily.longitude,
                    elevation = weatherDaily.elevation,
                    onRemove = onRemove,
                    onEdit = onEdit
                )
                DatePlanchette(days = weekDays, currentDay = currentDay)
                LazyColumn {
                    item {
                        Column(
                            modifier = modifier
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ) {
                            WeatherTemperatureGraphLow(
                                graphValues = weatherDaily.apparentTemperatureMin,
                            )
                            WeatherTemperatureGraphHigh(graphValues = weatherDaily.apparentTemperatureMax)
                            if (weatherDaily.rainSum.sum() != 0.0) {
                                WeatherNamedGraph(
                                    graphValues = weatherDaily.rainSum,
                                    titlePic = R.drawable.icon_water_drop,
                                    positiveColor = WeatherConcreteColors.rainValue,
                                    negativeColor = WeatherConcreteColors.rainValue,

                                    )
                            }
                            if (weatherDaily.snowfallSum.sum() != 0.0) {
                                WeatherNamedGraph(
                                    graphValues = weatherDaily.snowfallSum,
                                    titlePic = R.drawable.icon_snowflake,
                                    positiveColor = WeatherConcreteColors.rainValue,
                                    negativeColor = WeatherConcreteColors.rainValue,
                                )

                            }
                            WeatherNamedGraph(
                                graphValues = weatherDaily.windSpeed10mMax,
                                titlePic = R.drawable.icon_windy,
                                positiveColor = WeatherConcreteColors.windValue,
                                negativeColor = WeatherConcreteColors.windValue,

                                )
                            WeatherNamedGraph(
                                graphValues = weatherDaily.uvIndexMax,
                                titlePic = R.drawable.icon_ultraviolet,
                                positiveColor = WeatherConcreteColors.uvValue,
                                negativeColor = WeatherConcreteColors.uvValue,

                            )
                        }
                    }
                }
            } else {
                AnimatedContent(
                    tint = WeatherConcreteColors.uvValue
                )
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

