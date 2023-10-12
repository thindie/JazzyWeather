package com.example.thindie.weather_concrete.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
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
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.presentation.R
import com.example.thindie.weather_concrete.components.ConcreteCalendar
import com.example.thindie.weather_concrete.components.ConcreteTitle
import com.example.thindie.weather_concrete.components.graphcomposables.WeatherGraph
import com.example.thindie.weather_concrete.viewmodel.WeatherConcreteViewModel

@Composable
internal fun WeatherConcreteScreen(
    modifier: Modifier = Modifier,
    screenState: WeatherConcreteViewModel.ConcreteWeatherScreenState,
    onEdit: (String) -> Unit,
    onRememberChanges: (WeatherDaily) -> Unit,
    onClickConcreteDay: (Long) -> Unit,
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
            .padding(horizontal = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        with(screenState) {
            if (weatherDaily != null) {
                ConcreteTitle(
                    weatherDaily = weatherDaily,
                    onRememberChanges = onRememberChanges,
                    sunrise = screenState.sunrise,
                    sunset = screenState.sunset
                )
                ConcreteCalendar(
                    digits = screenState.weekDays,
                    days = screenState.namedWeekDays,
                    onClickConcreteDay = { clickedOn ->
                        shouldShowAdditionalSection = clickedOn != lastClickedSection
                        lastClickedSection = clickedOn
                        if (shouldShowAdditionalSection) onClickConcreteDay(clickedOn)
                    }
                )
                AnimatedVisibility(visible = shouldShowAdditionalSection) {
                    if (screenState.isHourlyLoading) CircularProgressIndicator()
                    else Text(text = screenState.concreteWeatherHourly?.place.orEmpty())
                }

                LazyColumn(contentPadding = PaddingValues(vertical = 10.dp)) {
                    item {
                        WeatherGraph(
                            list = weatherDaily.apparentTemperatureMax,
                            graphIcon = R.drawable.icon_temp_high,
                            iconTint = Color.Red,
                            firstColorComponent = Color.Red,
                            secondColorComponent = Color.Blue
                        )
                        WeatherGraph(
                            list = weatherDaily.apparentTemperatureMin,
                            graphIcon = R.drawable.icon_low_temp,
                            iconTint = Color.Blue,
                            firstColorComponent = Color.Red,
                            secondColorComponent = Color.Blue
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

