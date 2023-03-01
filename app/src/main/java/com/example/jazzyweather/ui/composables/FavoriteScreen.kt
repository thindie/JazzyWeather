package com.example.jazzyweather.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.ui.composables.util.*

@Composable
fun FavoriteWeathersList(
    favorites: List<Weather>,
    onFavoriteDelete: (String) -> Unit,
    onClickWeather: (Weather) -> Unit,
) {
    OnScreen() {
        LazyColumn(
            modifier = Modifier
                .basicDimensions()
                .fillMaxHeight()
        ) {
            items(favorites) {
                ElevatedCard(
                    Modifier
                        .basicDimensions()
                        .clickable { onClickWeather(it) }) {
                    it.place.Body()
                    it.latitude.toString().Label()
                    it.longitude.toString().Label()
                    IconButton(onClick = { onFavoriteDelete(it.place) }) {
                        Icon(imageVector = icons().Favorite, contentDescription = null)
                    }
                }
            }
        }
    }
}