package com.example.thindie.weathers_site_favorites.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.OneHourWeather
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.presentation.R

@Composable
internal fun WeatherHourlySection(
    modifier: Modifier = Modifier,
    hourly: WeatherHourly,
    currentHour: Int,
    onDecodeWeatherCode: (Int) -> Int,
    onClickConcrete: (ForecastAble) -> Unit,
) {
    var shouldShowGraph by remember {
        mutableStateOf(false)
    }

    val icon = animateIntAsState(
        targetValue = if (shouldShowGraph)
            R.drawable.icon_chevron_down else R.drawable.icon_chevron_up,
        label = ""
    )


    Column(
        modifier = modifier

            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ClickableText(text = AnnotatedString(text = hourly.place),
                style = MaterialTheme
                    .typography
                    .headlineMedium
                    .copy(color = MaterialTheme.colorScheme.onSurface),
                onClick = { onClickConcrete(hourly) })

            IconButton(onClick = { shouldShowGraph = shouldShowGraph.not() }) {
                Icon(
                    painter = painterResource(id = icon.value),
                    contentDescription = null
                )
            }
        }

        AnimatedVisibility(visible = shouldShowGraph) {
            val state = rememberLazyListState()
            LaunchedEffect(shouldShowGraph) {
                state.scrollToItem(currentHour)
            }

            LazyRow(
                state = state,
                modifier = modifier.background(
                    MaterialTheme
                        .colorScheme
                        .background
                        .TransGradientVerticalInverse()
                )
            ) {
                items(
                    hourly.getHourlyForecast(),
                    key = { it.hashCode() }) { item: OneHourWeather ->
                    HourlyUnit(
                        time = item.time,
                        oneHourWeather = item,
                        getDecodedWeatherCode = onDecodeWeatherCode
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherHourlySection() {
    JazzyWeatherTheme {

    }
}
