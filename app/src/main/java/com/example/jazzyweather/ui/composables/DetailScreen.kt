package com.example.jazzyweather.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.ui.composables.util.*

@Composable
fun DetailScreen(weather: Weather, onClickAdd: (Weather) -> Unit, onClickDelete: (String) -> Unit) {
    OnScreen {

        ElevatedCard(
            modifier = Modifier
                .basicDimensions()
                .padding(bottom = endTwelvePadding, top = eightStartPadding)
        ) {
            weather.place.Headline()
            weather.sunrise[0].toString().Body()
        }
    }
}
