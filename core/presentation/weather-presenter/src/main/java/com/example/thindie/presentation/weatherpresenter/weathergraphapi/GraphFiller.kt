package com.example.thindie.presentation.weatherpresenter.weathergraphapi

interface GraphFiller {
    val logicalMin: Int
    val logicalMax: Int
    val metricSystem: String
    val title: String
}

data class WeatherGraphFiller(
    override val logicalMin: Int,
    override val logicalMax: Int,
    override val metricSystem: String,
    override val title: String
): GraphFiller

val tempApparentMaximum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "C",
    title = "real feel",
)
val tempApparentMinimum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "C",
    title = "real feel",
)

val precipitation = WeatherGraphFiller(
    logicalMax = 100,
    logicalMin = 0,
    metricSystem = "percents",
    title = "precipitation",
)

val rainSum = WeatherGraphFiller(
    logicalMax = 30,
    logicalMin = 0,
    metricSystem = "mm",
    title = "rain sum",
)

val showersSum = WeatherGraphFiller(
    logicalMax = 50,
    logicalMin = 0,
    metricSystem = "mm",
    title = "showers sum",
)

val snowFall = WeatherGraphFiller(
    logicalMax = 30,
    logicalMin = 0,
    metricSystem = "cm",
    title = "snowfall sum",
)

val tempMaximum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "C",
    title = "temperature",
)
val tempMinimum = WeatherGraphFiller(
    logicalMax = 40,
    logicalMin = -40,
    metricSystem = "C",
    title = "temperature",
)

val windGustsMinimum = WeatherGraphFiller(
    logicalMax = 70,
    logicalMin = 0,
    metricSystem = "m/s",
    title = "wind gusts",
)
val windGustsMaximum = WeatherGraphFiller(
    logicalMax = 70,
    logicalMin = 0,
    metricSystem = "m/s",
    title = "wind gusts",
)