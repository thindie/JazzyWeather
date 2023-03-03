package com.example.jazzyweather.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.jazzyweather.domain.Possibility
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.ui.composables.util.*
import com.example.thindie.wantmoex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteWeathersList(
    favorites: List<Weather>,
    possibilities: List<Possibility>,
    onFavoriteDelete: (String) -> Unit,
    onClickWeather: (Weather) -> Unit,
    onClickPossibility: (Possibility) -> Unit,
) {
    OnScreen() {
        LazyColumn(
            modifier = Modifier
                .basicDimensions()
                .fillMaxHeight()
        ) {
            items(favorites) {
                OutlinedCard(
                    onClick = { onClickWeather(it) },
                    Modifier
                        .basicDimensions()
                        .eightStartPadding()
                        .twelveEndPadding()
                        .padding(bottom = twelve),
                    shape = ShapeDefaults.ExtraLarge,

                    colors = ountlinedCards(),
                    elevation = CardDefaults.cardElevation(defaultElevation = twelve),
                    border = BorderStroke(two, color = Color.Transparent)
                ) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Column(

                        ) {
                            it.temperature.toString()
                                .Headline(color = if (it.isPlus) Color.Red else Color.Blue)

                            IconButton(onClick = { onFavoriteDelete(it.place) }) {
                                Icon(imageVector = icons().Favorite, contentDescription = null)
                            }
                        }
                        Column(

                        ) {
                            it.place.Body()
                            stringResource(id = R.string.falls).plus(stringResource(id = it.precipitation_sum[0].expectingFalls()))
                                .Body()
                        }
                    }
                }
            }
        }
        LazyRow(modifier = Modifier.basicDimensions()) {
            items(possibilities) {
                OutlinedCard(
                    Modifier
                        .height(eighty)
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


@StringRes
fun Double.expectingFalls(): Int {
    return if (this == 0.0) R.string.expecting else R.string.unexpecting
}