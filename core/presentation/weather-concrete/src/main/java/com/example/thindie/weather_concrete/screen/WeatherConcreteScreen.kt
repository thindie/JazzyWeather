package com.example.thindie.weather_concrete.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.toHalf
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.presentation.R
import com.example.thindie.weather_concrete.components.ConcreteCalendar
import com.example.thindie.weather_concrete.components.ConcreteTitle
import com.example.thindie.weather_concrete.components.HourlyUnit
import com.example.thindie.weather_concrete.components.graphcomposables.WeatherGraph
import com.example.thindie.weather_concrete.components.graphcomposables.rememberWeatherGraphState
import com.example.thindie.weather_concrete.components.rememberCalendarState
import com.example.thindie.weather_concrete.viewmodel.WeatherConcreteViewModel
import java.text.DecimalFormat
import java.text.NumberFormat

@Composable
internal fun WeatherConcreteScreen(
    modifier: Modifier = Modifier,
    screenState: WeatherConcreteViewModel.ConcreteWeatherScreenState,
    onRememberChanges: (WeatherDaily) -> Unit,
    onClickConcreteDay: (Long) -> Unit,
    getDecodedWeatherIcon: (Int) -> Int,
    onRemove: (String) -> Unit,
) {
    var shouldShowAdditionalSection by remember {
        mutableStateOf(false)
    }

    var lastClickedSection by remember {
        mutableLongStateOf(0L)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
    ) {

        with(screenState) {
            if (weatherDaily != null) {
                ConcreteTitle(
                    weatherDaily = weatherDaily,
                    onRememberChanges = onRememberChanges,
                    sunrise = screenState.sunrise,
                    sunset = screenState.sunset,
                    onDeletePlace = onRemove
                )
                ConcreteCalendar(
                    state = rememberCalendarState(
                        digits = screenState.weekDays,
                        days = screenState.namedWeekDays
                    ),
                    onClickConcreteDay = { clickedOn ->
                        shouldShowAdditionalSection = clickedOn != lastClickedSection
                        lastClickedSection = clickedOn
                        if (shouldShowAdditionalSection) onClickConcreteDay(clickedOn)
                    }
                )
                AnimatedVisibility(visible = shouldShowAdditionalSection) {
                    if (screenState.isHourlyLoading) CircularProgressIndicator()
                    else {
                        if (concreteWeatherHourly != null)
                            LazyRow(modifier = Modifier.padding(vertical = 32.dp)) {
                                items(concreteWeatherHourly.getHourlyForecast()) {
                                    HourlyUnit(
                                        time = it.time,
                                        oneHourWeather = it,
                                        getDecodedWeatherCode = getDecodedWeatherIcon
                                    )
                                }
                            }
                    }
                }

                LazyColumn(contentPadding = PaddingValues(vertical = 10.dp)) {
                    item {

                        WeatherGraph(
                            modifier,
                            weatherGraphState = rememberWeatherGraphState(
                                graphIcon = R.drawable.icon_low_temp,
                                iconTint = MaterialTheme.colorScheme.onSurface,
                                list = average(
                                    weatherDaily.apparentTemperatureMin,
                                    weatherDaily.apparentTemperatureMax
                                ),
                                firstColorComponent = MaterialTheme.colorScheme.error,
                                secondColorComponent = MaterialTheme.colorScheme.surfaceTint,
                                title = R.string.text_label_real_feel_max
                            )
                        )
                        if (weatherDaily.precipitationSum.any { it > 0.0 })
                            WeatherGraph(
                                weatherGraphState = rememberWeatherGraphState(
                                    graphIcon = R.drawable.icon_water_drop,
                                    iconTint = MaterialTheme.colorScheme.inversePrimary,
                                    list = weatherDaily.precipitationSum,
                                    firstColorComponent = MaterialTheme.colorScheme.inversePrimary,
                                    secondColorComponent = MaterialTheme.colorScheme.inversePrimary,
                                    title = R.string.text_label_real_feel_precip
                                )
                            )

                        if (weatherDaily.snowfallSum.any { it > 0.0 })
                            WeatherGraph(
                                weatherGraphState = rememberWeatherGraphState(
                                    graphIcon = R.drawable.icon_snowflake,
                                    iconTint = MaterialTheme.colorScheme.inversePrimary,
                                    list = weatherDaily.snowfallSum,
                                    firstColorComponent = Color.White,
                                    secondColorComponent = MaterialTheme.colorScheme.onSurface,
                                    title = R.string.text_label_real_feel_snow
                                )
                            )
                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }

    }
}

private fun average(a1: List<Double>, a2: List<Double>): List<Double> {
    return a1.zip(a2) { first: Double, second: Double ->
        first.toInt().plus(second.toInt()).div(2.00)

    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoritesScreen() {
    JazzyWeatherTheme {

    }
}

