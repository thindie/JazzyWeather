package com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.portraitscreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.domain.weatherprovider.entity.Weather

@Composable
fun SelectedWeatherPortraitScreen (
    weatherList: List<Weather>,
    onSelectedDestination: (String) -> Unit,
    onChangePinnedStatus: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn() {
        items(weatherList) { weather ->

        }
    }
}