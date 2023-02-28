package com.example.jazzyweather.ui.composables

import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.ui.composables.util.Body
import com.example.jazzyweather.ui.composables.util.Headline
import com.example.jazzyweather.ui.composables.util.basicDimens

@Composable
fun DetailScreen(weather: Weather) {
    ElevatedCard(modifier = Modifier.basicDimens()) {
            weather.place.Headline()
            weather.sunrise[0].toString().Body()
    }
}
