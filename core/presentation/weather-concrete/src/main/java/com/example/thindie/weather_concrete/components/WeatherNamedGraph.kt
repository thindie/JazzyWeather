package com.example.thindie.weather_concrete.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.utils.toVisualCustomizersList

@Composable
internal fun WeatherNamedGraph(
    modifier: Modifier = Modifier,
    graphValues: List<Double>,
    @DrawableRes titlePic: Int,
    animationTime: Int = 600,
    columnWidth: Dp = 40.dp,
    positiveColor: Color,
    negativeColor: Color,
) {

    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {

        Icon(
            modifier = modifier
                .padding(all = 20.dp)
                .size(36.dp)
            ,
            painter = painterResource(id = titlePic),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )

        WeatherForecastColumnGraph(
            modifier = modifier
                .clip(RoundedCornerShape(20.dp))
                .background(
                    WeatherConcreteColors.unitColors
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
            customizersList = graphValues.map { it.toFloat() }.toVisualCustomizersList(
                positiveColor = positiveColor,
                negativeColor = negativeColor,
                scope = rememberCoroutineScope(),
                animationTime = animationTime
            ), columnWidth = columnWidth
        )

    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherNamedGraph() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {

    }
}
