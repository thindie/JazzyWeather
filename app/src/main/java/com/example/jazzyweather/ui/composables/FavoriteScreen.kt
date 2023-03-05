package com.example.jazzyweather.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.jazzyweather.data.whatWeatherForHuman
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.ui.WeatherHourlyUIModel
import com.example.jazzyweather.ui.composables.util.*
import com.example.jazzyweather.ui.theme.md_theme_dark_onSurface
import com.example.jazzyweather.ui.theme.md_theme_light_onPrimaryContainer
import com.example.jazzyweather.ui.theme.md_theme_light_primaryContainer
import com.example.jazzyweather.ui.theme.md_theme_light_surface
import com.example.thindie.wantmoex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteWeathersList(
    favorites: List<WeatherHourlyUIModel>,
    possibilities: List<Possibility>,
    onFavoriteDelete: (String) -> Unit,
    onClickWeather: (WeatherHourlyUIModel) -> Unit,
    onClickPossibility: (Possibility) -> Unit,
) {

    OnScreen() {
        LazyColumn(
            modifier = Modifier
                .basicDimensions()
                .padding(top = twenty)
                .fillMaxHeight()
        ) {
            items(favorites) {
                OutlinedCard(
                    onClick = { onClickWeather(it) },
                    Modifier
                        .fillMaxWidth()
                        .height(hundredTwenty)
                        .eightStartPadding()
                        .twelveEndPadding()
                        .padding(bottom = twelve, top = twenty),
                    shape = ShapeDefaults.ExtraLarge,
                    colors = ountlinedCards(),
                    elevation = CardDefaults.cardElevation(defaultElevation = twenty),
                    border = BorderStroke(two, color = Color.Transparent)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = thirty)
                    ) {
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .eightStartPadding()
                                    .fillMaxHeight()
                            ) {
                                IconButton(onClick = { onFavoriteDelete(it.place) }) {
                                    Icon(
                                        imageVector = icons().Favorite,
                                        contentDescription = null,
                                        modifier = Modifier.scale(0.7f)
                                    )
                                }
                            }
                        }
                        item {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .twelveEndPadding()
                                    .fillMaxHeight()
                            ) {
                                Box(
                                    Modifier
                                        .size(sixty)
                                        .clip(ShapeDefaults.ExtraLarge)
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                    ) {
                                        it.temperature
                                            .toString()
                                            .withPlus(it.isPlus)
                                            .Title(color = if (it.isPlus)
                                                Color.Red else
                                                    if (isSystemInDarkTheme()) color().primary
                                                    else Color.Blue)
                                    }
                                }
                            }
                        }
                        item {
                            Column {
                                it.place.Title()
                                stringResource(id = R.string.falls)
                                    .plus(stringResource(id = it.precipitation_sum[0].expectingFalls()))
                                    .Body(color = color().secondary)
                                it.weathercode.whatWeatherForHuman()
                                .Body(color = color().secondary)
                                 if(it.windspeed_10m_max.isWindy()){
                                     stringResource(id = R.string.windy)
                                         .Body(color = Color.Red)}

                            }
                        }

                    }
                }
                LazyRow() {
                    item {
                        HourlyCard(list = it.precipitation, R.string.fallz)
                    }
                    item {
                        HourlyCard(list = it.temperature_2m, R.string.tcz)
                    }
                    item {
                        HourlyCard(list = it.windspeed_10m_hourly, R.string.windz)
                    }

                }
            }
            item {
                LazyRow(
                    modifier = Modifier.basicDimensions(),
                    contentPadding = PaddingValues(start = two, end = two)
                ) {
                    items(possibilities) {
                        OutlinedCard(
                            Modifier
                                .height(eighty)
                                .twelveEndPadding()
                                .width(extraWide)
                                .clickable { onClickPossibility(it) },
                            shape = ShapeDefaults.ExtraLarge,
                            elevation = CardDefaults.cardElevation(defaultElevation = two),
                            border = BorderStroke(two, color = Color.Transparent)
                        ) {
                            Column(
                                Modifier
                                    .basicDimensions(eighty)
                                    .padding(start = thirty),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                it.place.Headline()
                                Row(Modifier.padding(top = two)) {
                                    it.latitude.toString().Label()
                                    Spacer(
                                        modifier = Modifier
                                            .padding(two)
                                            .size(two)
                                    )
                                    it.longitude.toString().Label()
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}


@StringRes
fun Double.expectingFalls(): Int {
    return if (this != 0.0) R.string.expecting else R.string.unexpecting
}

private fun String.withPlus(boolean: Boolean) : String{
    return if(boolean) "+".plus(this) else this
}
private fun List<Double>.isWindy() : Boolean{
    return this.any { it > 20.0 }
}