package com.example.thindie.presentation.weatherpresenter.screen.concreteweather.portraitScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections.WeatherPrimarySection
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections.WeatherSlaveSection

@Composable
fun WeatherPresenterPortraitScreen(
    modifier: Modifier = Modifier,
    weather: Weather,
    onClick: (String, Float, Float) -> Unit,
) {
    Column(modifier) {
        WeatherPrimarySection(
            place = weather.place,
            weatherCode = weather.weathercode,
            temperature = weather.temperature,
            plus = weather.isPlus,
            sunset = weather.sunset,
            sunrise = weather.sunrise,
            windDirection = weather.winddirection,
            windSpeed = weather.windspeed,
            time = weather.time,
            longitude = weather.longitude,
            latitude = weather.latitude,
        ) { pinStatus, latitude, longitude ->
            onClick(pinStatus, latitude, longitude)
        }
        WeatherSlaveSection(
            modifier = modifier,
            longitude = weather.longitude,
            latitude = weather.latitude,
            times = weather.sunrise,
            apparentTemperatureMax = weather.apparentTemperatureMax,
            precipitationSum = weather.precipitationSum,
            rainSum = weather.rainSum,
            showersSum = weather.showersSum,
            precipitationSum1 = weather.precipitationSum,
            snowfallSum = weather.snowfallSum,
            temperatureMax = weather.temperatureMaxHourly,
            temperatureMin = weather.temperatureMinHourly,
            weatherCodes = weather.weatherCodesHourly,
            windspeed10mMax = weather.windspeed10mMaxHourly,
            windgusts10mMax = weather.windgusts10mMaxHourly
        )
    }
}