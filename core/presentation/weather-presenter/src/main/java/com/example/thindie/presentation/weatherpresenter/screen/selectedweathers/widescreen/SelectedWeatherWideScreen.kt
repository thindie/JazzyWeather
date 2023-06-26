package com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.widescreen

import WeatherContentRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.domain.weatherprovider.entity.Weather

@Composable
fun LocationsWideScreen(
    weatherList: List<Weather>,
    onSelectedDestination: (String) -> Unit,
    onChangePinnedStatus: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        LazyColumn() {
            items(weatherList) { weatherPlace ->

                Column(modifier = modifier.fillMaxWidth()) {
                    WeatherInformationSection(
                        temperature = weatherPlace.temperature,
                        windspeed = weatherPlace.windspeed,
                        sunrise = weatherPlace.sunrise,
                        sunset = weatherPlace.sunset,
                        place = weatherPlace.place,
                        plus = weatherPlace.isPlus,
                        precipitation = weatherPlace.precipitationSum,
                        wind = weatherPlace.windgusts10mMaxHourly,
                        onSelectedDestination = onChangePinnedStatus,
                    ) { place ->
                        onSelectedDestination(place)
                    }
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    WeatherContentRow(
                        weatherCodesHourly = weatherPlace.weatherCodesHourly,
                        timesHourly = weatherPlace.timesHourly,
                        windHourly = weatherPlace.windgusts10mMaxHourly,
                        temperature = weatherPlace.temperatureMaxHourly,
                        temperatureMin = weatherPlace.temperatureMinHourly,
                        timeNow = weatherPlace.time
                    )
                }
            }
        }

    }
}
