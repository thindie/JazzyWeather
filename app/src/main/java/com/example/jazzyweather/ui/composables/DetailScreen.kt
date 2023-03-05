package com.example.jazzyweather.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.jazzyweather.data.whatWeatherForHuman
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.ui.WeatherUIModel
import com.example.jazzyweather.ui.composables.util.*
import com.example.thindie.wantmoex.R


@Composable
fun DetailScreen(weather: WeatherUIModel, onClickAdd: (WeatherUIModel) -> Unit) {
    OnScreen {

        ElevatedCard(
            modifier = Modifier
                .basicDimensions()
                .eightStartPadding()
                .twelveEndPadding()
                .padding(bottom = twelve, top = eight),
            shape = ShapeDefaults.ExtraLarge
        ) {
            IconButton(onClick = { onClickAdd(weather) }) {
                Icon(imageVector = icons().Favorite, contentDescription = null)
            }
            LazyRow(contentPadding = PaddingValues(eight)) {

                item {
                    Column(
                        Modifier
                            .eightStartPadding()
                            .twelveEndPadding()
                    ) {
                        SpacerTwelve()
                        Row() {
                            weather.place.Display(color = color().tertiary)
                            Spacer(modifier = Modifier.size(sixty))
                            Column {
                                weather.latitude.toString()
                                    .Label(color = color().onPrimaryContainer)
                                weather.longitude.toString()
                                    .Label(color = color().onPrimaryContainer)
                            }
                        }
                        Spacer(modifier = Modifier.size(sixty))

                        stringResource(
                            id = R.string.temperature, weather.temperature.toString()
                        ).Display(color = if (weather.isPlus) color().error else color().primary)
                        Row() {
                            stringResource(
                                id = R.string.weather_code,
                                weather.weathercode.whatWeatherForHuman()
                            ).Body()
                            Spacer(Modifier.padding(eight))
                            stringResource(
                                id = R.string.windSpeed,
                                weather.windspeed.toString()
                            ).Body()
                            SpacerTwelve()
                        }
                        Row() {
                            stringResource(id = R.string.sunrise).plus(weather.sunrise[0].convertTime())
                                .Body(color = color().secondary)
                            SpacerTwelve()
                            stringResource(id = R.string.sunset).plus(weather.sunset[0].convertTime())
                                .Body(color = color().inversePrimary)
                        }

                    }
                }

            }
            Spacer(modifier = Modifier.size(thirty))
            Divider()
            val weatherDays = weather.times.map {
                it.replace("\\d{4}[-]".toRegex(), "").replace("-", ".")
            }
            WeatherSevenDays(list = weatherDays) { Body(color = color().surfaceTint) }
            Divider()
            LazyColumn(Modifier.padding(start = eight)) {
                item {
                    SpacerTwelve()

                    stringResource(id = R.string.t_max).Body()
                    WeatherSevenDays(list = weather.temperature_2m_max) { Body(color = color().secondary) }

                    stringResource(id = R.string.t_min).Body()
                    WeatherSevenDays(list = weather.temperature_2m_min) { Body(color = color().secondary) }
                    Divider()

                    if (weather.precipitation_sum.isRequired()) {
                        stringResource(id = R.string.precipitation_sum).Body()
                        WeatherSevenDays(list = weather.precipitation_sum) { Label(color = color().tertiary) }
                    }


                    if (weather.rain_sum.isRequired()) {
                        stringResource(id = R.string.rain_sum).Body()
                        Log.d("SERVICE_TAG", "${weather.rain_sum.isRequired()}")
                        WeatherSevenDays(list = weather.rain_sum) { Label(color = color().tertiary) }
                    }

                    if (weather.snowfall_sum.isRequired()) {
                        stringResource(id = R.string.snowfall_sum).Body()
                        Log.d("SERVICE_TAG", "${weather.snowfall_sum.isRequired()}")
                        WeatherSevenDays(list = weather.snowfall_sum) { Label(color = color().tertiary) }
                    }
                    Log.d("SERVICE_TAG", "${weather.snowfall_sum.isRequired()}")

                    if (weather.showers_sum.isRequired()) {
                        stringResource(id = R.string.showers_sum).Body()
                        Log.d("SERVICE_TAG", "${weather.showers_sum.isRequired()}")
                        WeatherSevenDays(list = weather.showers_sum) { Label() }
                    }

                }

            }

        }
    }
}


@Composable
fun <T> WeatherSevenDays(list: List<T>, string: @Composable String.(modifier: Modifier) -> Unit) {
    LazyHorizontalGrid(rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Ar_C,
        contentPadding = PaddingValues(two),
        userScrollEnabled = false,
        modifier = Modifier
            .padding(two)
            .height(thirty)
            .fillMaxWidth(),
        content = {
            items(list) {

                it.toString().string(modifier = Modifier.padding(two))


            }
        })
}

private fun List<Double>.isRequired(): Boolean {
    var sum = 0.0
    this.forEach {
        sum = sum.plus(it.toDouble())
    }

    return sum > 0.0
}


fun String.convertTime(): String {

    return this.replaceBefore("T", "").replaceFirstChar { " " }
}
