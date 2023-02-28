package com.example.jazzyweather.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.ui.composables.util.Body
import com.example.jazzyweather.ui.composables.util.Label
import com.example.jazzyweather.ui.composables.util.basicDimens

@Composable
fun FavoriteWeathersList(favorites: List<Weather>, onClickWeather: (Weather) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .basicDimens()
            .fillMaxHeight()
    ) {
        items(favorites) {
            ElevatedCard(
                Modifier
                    .basicDimens()
                    .clickable { onClickWeather(it) }) {
                it.place.Body()
                it.latitude.toString().Label()
                it.longitude.toString().Label()
            }
        }
    }
}