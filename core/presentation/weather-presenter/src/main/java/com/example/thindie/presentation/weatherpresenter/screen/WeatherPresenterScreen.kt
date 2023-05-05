package com.example.thindie.presentation.weatherpresenter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.BuildForecastGraph
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.tempApparentMaximum

@Composable
fun WeatherPresenterScreen(isWideScreen: Boolean, weather: Weather) {
    if (isWideScreen)
        WeatherPresenterWideScreen(weather = weather)
    else WeatherPresenterPortraitScreen(weather = weather)
}

@Composable
fun WeatherPresenterPortraitScreen(modifier: Modifier = Modifier, weather: Weather) {
    Column(modifier) {
        WeatherPrimarySection(
            place = weather.place,
            weatherCode = weather.weathercode,
            temperature = weather.temperature,
            plus = weather.isPlus,
            sunset = weather.sunset,
            sunrise = weather.sunrise
        )
        WeatherSlaveSection(
            modifier = modifier,
            longitude = weather.longitude,
            latitude = weather.latitude,
            apparentTemperatureMax = weather.apparentTemperatureMax,
            precipitationSum = weather.precipitationSum,
            rainSum = weather.rainSum,
            showersSum = weather.showersSum,
            precipitationSum1 = weather.precipitationSum,
            snowfallSum = weather.snowfallSum,
            temperatureMax = weather.temperatureMax,
            temperatureMin = weather.temperatureMin,
            weatherCodes = weather.weatherCodes,
            windspeed10mMax = weather.windspeed10mMax,
            windgusts10mMax = weather.windgusts10mMax
        )
    }
}

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
    windgusts10mMax: List<Double>
) {
    CoordinatesSection(modifier, longitude, latitude)
    apparentTemperatureMax.BuildForecastGraph(tempApparentMaximum)

}


@Suppress("LongParameterList")
@Composable
fun WeatherPrimarySection(
    place: String,
    weatherCode: Int,
    temperature: Double,
    plus: Boolean,
    sunset: List<String>,
    sunrise: List<String>
) {

}

@Composable
fun WeatherPresenterWideScreen(modifier: Modifier = Modifier, weather: Weather) {
    Row(modifier) {

    }
}

@Composable
fun CoordinatesSection(modifier: Modifier = Modifier, longitude: Float, latitude: Float) {
    OutlinedCard(modifier = modifier.size(36.dp)) {
        longitude.toString().BodyText()
        latitude.toString().BodyText()
    }
}



