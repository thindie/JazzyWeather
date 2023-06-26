package com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.BuildDaysLine
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.graph
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.BuildColumnGraph
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.precipitation
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.rainSums
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.showersSums
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.snowFall
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.tempApparentMaximum

@Suppress("LongParameterList")
@Composable
fun WeatherSlaveSection(
    modifier: Modifier,
    longitude: Float,
    latitude: Float,
    apparentTemperatureMax: List<Double>,
    precipitationSum: List<Double>,
    rainSum: List<Double>,
    showersSum: List<Double>,
    precipitationSum1: List<Double>,
    snowfallSum: List<Double>,
    temperatureMax: List<Double>,
    temperatureMin: List<Double>,
    weatherCodes: List<Int>,
    windspeed10mMax: List<Double>,
    windgusts10mMax: List<Double>,
    times: List<String>,
) {
    Log.d("SERVICE_TAG_WIDE", "WIDE")
    CoordinatesSection(longitude = longitude, latitude = latitude)
    times.BuildDaysLine()
    LazyColumn() {
        item {
            graph(apparentTemperatureMax)
            { apparentTemperatureMax.BuildColumnGraph(tempApparentMaximum) }
        }
        item {
            graph(precipitationSum)
            { precipitationSum.BuildColumnGraph(precipitation) }
        }
        item {
            graph(rainSum)
            { rainSum.BuildColumnGraph(rainSums) }

        }
        item {
            graph(showersSum)
            { showersSum.BuildColumnGraph(showersSums) }

        }
        item {
            graph(snowfallSum)
            { snowfallSum.BuildColumnGraph(graphFiller = snowFall) }
        }
    }
}