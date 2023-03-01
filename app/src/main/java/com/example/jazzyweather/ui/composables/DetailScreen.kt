package com.example.jazzyweather.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jazzyweather.data.whatWeatherForHuman
import com.example.jazzyweather.domain.Weather
import com.example.jazzyweather.ui.composables.util.*
import com.example.thindie.wantmoex.R

@Composable
fun DetailScreen(weather: Weather, onClickAdd: (Weather) -> Unit, onClickDelete: (String) -> Unit) {
    OnScreen {

        ElevatedCard(
            modifier = Modifier
                .basicDimensions()
                .eightStartPadding()
                .twelveEndPadding()
                .padding(bottom = endTwelvePadding, top = eightStartPadding)
        ) {
            SpacerTwelve()
            weather.place.Headline()
            SpacerTwelve()
            stringResource(
                id = R.string.temperature,
                weather.temperature.toString()
            ).Display(color = if (weather.isPlus) color().error else color().primary)
            Spacer(Modifier.padding(30.dp))
            Divider()
            stringResource(
                id = R.string.weather_code,
                weather.weathercode.whatWeatherForHuman()
            ).Body()
            SpacerTwelve()
            Row {
                stringResource(id = R.string.precipitation_sum, weather.precipitation_sum[0]).Body()
            }
        }
    }
}
