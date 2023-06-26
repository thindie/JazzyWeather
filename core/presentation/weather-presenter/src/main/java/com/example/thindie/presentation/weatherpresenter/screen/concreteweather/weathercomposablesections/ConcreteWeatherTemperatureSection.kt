package com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.R
import com.example.thindie.presentation.ShortWeatherPresenter
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.designsystem.textutil.MiniText

@Composable
fun TemperatureSection(
    modifier: Modifier = Modifier,
    place: String,
    temperature: Double,
    plus: Boolean,
    weatherPresenter: ShortWeatherPresenter
) {
    val values = PaddingValues(all = 5.dp)
    Card(
        shape = ShapeDefaults.ExtraLarge,
        modifier = modifier
            .padding(values)
            .width(400.dp)
            .height(120.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier
                    .fillMaxWidth(0.35f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                WeatherSymbol(
                    weatherRes = weatherPresenter.weatherDrawable,
                    modifier = modifier
                        .size(72.dp),
                )

            }
            Column(modifier = modifier.padding(all = 10.dp)) {
                stringResource(id = R.string.place, "")
                    .MiniText()
                place.HeadLineText()
                weatherPresenter.nameType.BodyText()
                stringResource(
                    id = R.string.temperature_2m,
                    temperature.toString()
                )
                    .HeadLineText(
                        color = if (plus) Color.Red else Color.Blue
                    )
            }

        }
    }
}