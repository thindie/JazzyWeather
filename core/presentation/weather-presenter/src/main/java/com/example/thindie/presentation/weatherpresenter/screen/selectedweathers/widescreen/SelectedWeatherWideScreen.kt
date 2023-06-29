package com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.widescreen

import WeatherContentRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.domain.weatherprovider.entity.Weather

@Composable
fun LocationsWideScreen(
    weatherList: List<Weather>,
    onSelectedDestination: (String, Float, Float) -> Unit,
    onChangePinnedStatus: (String, Float, Float) -> Unit,
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
                    LazyRow() {
                        item {
                            WeatherInformationSection(
                                temperature = weatherPlace.temperature,
                                windspeed = weatherPlace.windspeed,
                                sunrise = weatherPlace.sunrise,
                                sunset = weatherPlace.sunset,
                                place = weatherPlace.place,
                                latitude = weatherPlace.latitude,
                                longitude =weatherPlace.longitude,
                                plus = weatherPlace.isPlus,
                                precipitation = weatherPlace.precipitationSum,
                                wind = weatherPlace.windgusts10mMaxHourly,
                                onSelectedDestination = onSelectedDestination,
                            ) { place, latitude, longitude ->
                                onChangePinnedStatus(place, latitude, longitude)
                            }
                        }
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
