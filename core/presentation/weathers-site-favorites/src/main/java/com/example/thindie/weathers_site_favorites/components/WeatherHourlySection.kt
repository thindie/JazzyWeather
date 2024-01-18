package com.example.thindie.weathers_site_favorites.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.designsystem.utils.TransGradientVertical
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
        targetValue = if (shouldShowGraph) R.drawable.icon_chevron_down else R.drawable.icon_chevron_up,
        label = ""
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .wrapContentHeight()
            .padding(all = 8.dp)
            .background(MaterialTheme.colorScheme.onPrimary.TransGradientVertical())
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ClickableText(
                modifier = Modifier.padding(start = 20.dp),
                text = AnnotatedString(text = hourly.place),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.inverseSurface),
                onClick = { onClickConcrete(hourly) })
            IconButton(onClick = { shouldShowGraph = shouldShowGraph.not() }) {
                Icon(
                    painter = painterResource(id = icon.value), contentDescription = null
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconTextSection(
                modifier = modifier.padding(start = 10.dp),
                icon = R.drawable.icon_celsius,
                title = hourly.getHourlyForecast()[currentHour].temperature2m.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall
            )


            IconTextSection(
                modifier = modifier,
                icon = onDecodeWeatherCode(hourly.getHourlyForecast()[currentHour].weatherCode),
                title = "",
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            if (hourly.getHourlyForecast()[currentHour].precipitation > 0.0)
                IconTextSection(
                    modifier = modifier,
                    icon = if (hourly.getHourlyForecast()[currentHour].snowfall > 0.0) R.drawable.icon_snowflake
                    else R.drawable.icon_water_drop,
                    title = hourly.getHourlyForecast()[currentHour].precipitation.toString(),
                    color = MaterialTheme.colorScheme.surfaceTint
                )

        }

        AnimatedVisibility(visible = shouldShowGraph) {
            val state = rememberLazyListState()
            LaunchedEffect(shouldShowGraph) {
                state.scrollToItem(currentHour)
            }

            LazyRow(
                state = state, modifier = modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(
                        MaterialTheme.colorScheme.background.TransGradientVertical()
                    )

            ) {
                items(
                    hourly.getHourlyForecast(),
                    key = OneHourWeather::hashCode) { item: OneHourWeather ->
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

