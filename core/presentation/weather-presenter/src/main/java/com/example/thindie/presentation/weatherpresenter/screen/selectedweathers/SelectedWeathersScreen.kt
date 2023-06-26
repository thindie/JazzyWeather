package com.example.thindie.presentation.weatherpresenter.screen.selectedweathers

import androidx.compose.runtime.Composable
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.portraitscreen.SelectedWeatherPortraitScreen
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.widescreen.LocationsWideScreen

@Composable
fun SelectedWeatherLocationsScreen(
    isWideScreen: Boolean,
    onSelectedDestination: (String) -> Unit,
    weatherList: List<Weather>,
    onChangePinnedStatus: (String) -> Unit,
) {
    if (isWideScreen) LocationsWideScreen(
        weatherList, onSelectedDestination, onChangePinnedStatus
    ) else SelectedWeatherPortraitScreen(
        weatherList, onSelectedDestination, onChangePinnedStatus
    )
}