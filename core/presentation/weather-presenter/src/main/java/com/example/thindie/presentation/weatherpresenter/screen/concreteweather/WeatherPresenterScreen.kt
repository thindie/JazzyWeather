package com.example.thindie.presentation.weatherpresenter.screen.concreteweather

import androidx.compose.runtime.Composable
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.portraitScreen.WeatherPresenterPortraitScreen
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.widescreen.WeatherPresenterWideScreen

@Composable
fun WeatherPresenterScreen(isWideScreen: Boolean, weather: Weather, onClick: (String, Float, Float) -> Unit) {
    if (isWideScreen)
        WeatherPresenterWideScreen(weather = weather) { pinStatus, latitude, longitude ->
            onClick(pinStatus, latitude, longitude)
        }
    else WeatherPresenterPortraitScreen(weather = weather) { pinStatus, latitude, longitude ->
        onClick(pinStatus, latitude, longitude)
    }
}