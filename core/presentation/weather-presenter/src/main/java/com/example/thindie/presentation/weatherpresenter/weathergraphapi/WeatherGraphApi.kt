package com.example.thindie.presentation.weatherpresenter.weathergraphapi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.textutil.BodyLargeText
import com.example.thindie.presentation.designsystem.textutil.MiniText


@Preview
@Composable
fun previewGraph() {
    com.example.thindie.presentation.designsystem.theme.JazzyWeatherTheme {
        listOf(15.00, 5.00, -3.00, 23.00, 40.00).BuildColumnGraph(graphFiller = tempMaximum)
    }
}

@Composable
fun List<Double>.BuildColumnGraph(
    graphFiller: GraphFiller
) {
    val values = PaddingValues(start = 12.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    Card(
        Modifier
            .padding(values)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = ShapeDefaults.ExtraLarge
    ) {
        Row(Modifier.padding(start = 25.dp, end = 8.dp, top = 4.dp)) {
            graphFiller.title.BodyLargeText()
            graphFiller.metricSystem.BodyLargeText()
        }
        LazyRow(modifier = Modifier.padding(start = 25.dp, end = 8.dp, bottom = 4.dp)
        ) {
            items(this@BuildColumnGraph) {
                ForecastGraphColumnDrawedUnit(
                    graphFiller = graphFiller,
                    valueToRepresent = it
                )
            }
        }
    }
}

@Composable
private fun ForecastGraphColumnDrawedUnit(
    modifier: Modifier = Modifier,
    graphFiller: GraphFiller,
    valueToRepresent: Double
) {

    Column(
        modifier
            .width(50.dp)
            .height(80.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Spacer(
            modifier
                .width(20.dp)
                .padding(start = 3.dp, end = 3.dp, top = 4.dp, bottom = 4.dp)
                .fillMaxHeight(
                    calculateColumnDrawedUnitHeight(
                        graphFiller.logicalMin,
                        graphFiller.logicalMax,
                        valueToRepresent
                    )
                )
                .background(
                    color = graphFiller.compareAndDraw(
                        graphFiller.drawCondition(
                            valueToRepresent
                        )
                    ), shape = ShapeDefaults.ExtraLarge.copy(all = CornerSize(4.dp))
                )

        )
        valueToRepresent.toString().MiniText()
    }
}

private fun calculateColumnDrawedUnitHeight(
    logicalMin: Int,
    logicalMax: Int,
    temperature: Double
): Float {
    return if (temperature > 0.0) {
        (((temperature / logicalMax.toDouble()).times(logicalMax)) * 0.1).toFloat() / 5
    } else if (temperature < 0.0) {
        (((temperature / (logicalMin.plus(logicalMin * 2))).times(logicalMin * -1)) * 0.1f).toFloat() / 7
    } else {
        0f
    }
}