package com.example.thindie.presentation.weatherpresenter.weathergraphapi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.designsystem.textutil.MiniText
import com.example.thindie.presentation.designsystem.theme.Shapes


@Composable
fun List<Double>.BuildForecastGraph(
    graphFiller: GraphFiller
) {
    Surface(
        Modifier
            .width(400.dp)
            .height(200.dp)
    ) {
        LazyRow() {
            item { graphFiller.metricSystem.HeadLineText() }
            items(this@BuildForecastGraph) {
                ForecastGraphColumnDrawedUnit(
                    logicalMin = graphFiller.logicalMin,
                    logicalMax = graphFiller.logicalMax,
                    temperature = it
                )
            }
        }
    }
}

@Composable
private fun ForecastGraphColumnDrawedUnit(
    modifier: Modifier = Modifier,
    logicalMin: Int,
    logicalMax: Int,
    temperature: Double
) {

    Column(
        modifier
            .width(40.dp)
            .height(200.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(
            modifier
                .width(20.dp)
                .padding(start = 3.dp, end = 3.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxHeight(calculateColumnDrawedUnitHeight(logicalMin, logicalMax, temperature))
                .background(if (temperature > 0.0) Color.Red else Color.Blue)
                .clip(Shapes.extraLarge)
        )
        temperature.toString().MiniText()
    }
}

private fun calculateColumnDrawedUnitHeight(
    logicalMin: Int,
    logicalMax: Int,
    temperature: Double
): Float {
    return if (temperature > 0.0) {
        (((temperature / logicalMax.toDouble()).times(logicalMax)) * 0.1).toFloat() / 4
    } else if (temperature < 0.0) {
        (((temperature / (logicalMin.plus(logicalMin * 2))).times(logicalMin * -1)) * 0.1f).toFloat() / 2
    } else {
        0f
    }
}