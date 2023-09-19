package com.example.thindie.weathers_site_favorites.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.SimpleVisualCustomizer
import com.example.thindie.designsystem.composables.VerticalIndicationColumn
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.toVisualCustomizersList


@Composable
internal fun WeatherForecastColumnGraph(
    modifier: Modifier = Modifier,
    customizersList: List<SimpleVisualCustomizer>,
    columnWidth: Dp = 30.dp,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        LazyRow(
            modifier = modifier
                .padding(all = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly,
            userScrollEnabled = false,
        ) {
            items(customizersList) { simpleVisualCustomizer ->
                VerticalIndicationColumn(
                    width = columnWidth,
                    animator = simpleVisualCustomizer,
                    textLabel = simpleVisualCustomizer.initialValue,
                    customizer = simpleVisualCustomizer
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherForecastColumnGraph() {
    JazzyWeatherTheme {
        val colorComponent =
            animateColorAsState(
                targetValue = Color.Cyan.copy(alpha = 0.1f), label = "",
                animationSpec = tween(delayMillis = 2000, durationMillis = 1555)
            )

        Column {
            WeatherForecastColumnGraph(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colors.onPrimary.copy(
                                    0.5f
                                ),
                                colorComponent.value
                            )
                        )
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                customizersList = listOf(1f, -2f, 3f, 5f, -2f, -9f, 22f).toVisualCustomizersList(
                    positiveColor = MaterialTheme.colors.error,
                    negativeColor = MaterialTheme.colors.primarySurface,
                    scope = rememberCoroutineScope(),
                    animationTime = 1700
                ), columnWidth = 40.dp
            )

            WeatherForecastColumnGraph(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colors.onError.copy(
                                    0.5f
                                ),
                                colorComponent.value
                            )
                        )
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                customizersList = listOf(
                    11f,
                    -22f,
                    3f,
                    22f,
                    -21f,
                    -9f,
                    12f
                ).toVisualCustomizersList(
                    positiveColor = MaterialTheme.colors.error,
                    negativeColor = MaterialTheme.colors.primarySurface,
                    scope = rememberCoroutineScope(),
                    animationTime = 700
                ), columnWidth = 40.dp
            )
        }


    }
}