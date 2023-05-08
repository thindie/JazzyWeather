package com.example.thindie.presentation.weatherpresenter.weathergraphapi

import androidx.compose.ui.graphics.Color

interface GraphFiller {
    val logicalMin: Int
    val logicalMax: Int
    val metricSystem: String
    val title: String
    val colorA: Color
    val colorB: Color
    val drawCondition: (Number) -> Boolean
    fun compareAndDraw(boolean: Boolean): Color
}

data class WeatherGraphFiller(
    override val logicalMin: Int,
    override val logicalMax: Int,
    override val metricSystem: String,
    override val title: String,
    override val colorA: Color,
    override val colorB: Color,
    override val drawCondition: (Number) -> Boolean
) : GraphFiller {
    override fun compareAndDraw(boolean: Boolean): Color {
        return if (boolean) colorA else colorB
    }
}

val tempApparentMaximum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "tC",
    title = "real feel, ",
    colorA = Color.Red,
    colorB = Color.Blue,
    drawCondition = { condition -> condition.toDouble() > 0.0 }
)
val tempApparentMinimum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "tC",
    title = "real feel, ",
    colorA = Color.Red,
    colorB = Color.Blue,
    drawCondition = { condition -> condition.toDouble() > 0.0 }
)

val precipitation = WeatherGraphFiller(
    logicalMax = 100,
    logicalMin = 0,
    metricSystem = "mm",
    title = "осадки, ",
    colorA = Color.Blue.copy(0.5f),
    colorB = Color.Blue,
    drawCondition = { condition -> condition.toDouble() > 0.5 }
)

val rainSums = WeatherGraphFiller(
    logicalMax = 30,
    logicalMin = 0,
    metricSystem = "mm",
    title = "дождь, ",
    colorA = Color.Blue.copy(0.5f),
    colorB = Color.Blue,
    drawCondition = { condition -> condition.toDouble() > 0.5 }
)

val showersSums = WeatherGraphFiller(
    logicalMax = 50,
    logicalMin = 0,
    metricSystem = "mm",
    title = "ливни, ",
    colorA = Color.Black.copy(0.5f),
    colorB = Color.Black,
    drawCondition = { condition -> condition.toDouble() > 0.7 }
)

val snowFall = WeatherGraphFiller(
    logicalMax = 30,
    logicalMin = 0,
    metricSystem = "cm",
    title = "снегопад, ",
    colorA = Color.White.copy(0.5f),
    colorB = Color.White,
    drawCondition = { condition -> condition.toDouble() > 2.0 }
)

val tempMaximum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "C",
    title = "tC, ",
    colorA = Color.Red,
    colorB = Color.Blue,
    drawCondition = { condition -> condition.toDouble() > 0.0 }
)
val tempMinimum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "C",
    title = "tC, ",
    colorA = Color.Red,
    colorB = Color.Blue,
    drawCondition = { condition -> condition.toDouble() > 0.0 }
)

val windGustsMinimum = WeatherGraphFiller(
    logicalMax = 70,
    logicalMin = 0,
    metricSystem = "m/s",
    title = "порывы ветра, ",
    colorA = Color.Yellow.copy(0.5f),
    colorB = Color.Yellow,
    drawCondition = { condition -> condition.toDouble() > 15.0 }
)
val windGustsMaximum = WeatherGraphFiller(
    logicalMax = 70,
    logicalMin = 0,
    metricSystem = "m/s",
    title = "порывы ветра, ",
    colorA = Color.Yellow.copy(0.5f),
    colorB = Color.Yellow,
    drawCondition = { condition -> condition.toDouble() > 15.0 }
)