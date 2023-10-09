package com.example.thindie.weather_concrete.components.graphcomposables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.utils.toVisualCustomizersList
import com.example.thindie.weather_concrete.components.WeatherConcreteColors

@Composable
internal fun WeatherTemperatureGraphHigh(
    modifier: Modifier = Modifier,
    graphValues: List<Double>,
    @DrawableRes titlePic: Int = com.example.thindie.presentation.R.drawable.icon_celsius,
    animationTime: Int = 600,
    columnWidth: Dp = 40.dp,
    positiveColor: Color = WeatherConcreteColors.positiveTemperature,
    negativeColor: Color = WeatherConcreteColors.negativeTemperature,
) {

    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = modifier
                    .padding(all = 20.dp)
                    .size(36.dp),
                painter = painterResource(id = titlePic),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
            Icon(
                modifier = modifier
                    .padding(all = 20.dp)
                    .size(36.dp),
                painter = painterResource(id = com.example.thindie.presentation.R.drawable.icon_low_temp),
                contentDescription = null,
                tint = WeatherConcreteColors.positiveTemperature
            )
        }


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

            ), columnWidth = columnWidth
        )

    }
}

