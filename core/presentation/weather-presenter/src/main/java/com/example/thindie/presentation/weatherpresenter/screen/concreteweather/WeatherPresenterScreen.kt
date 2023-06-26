package com.example.thindie.presentation.weatherpresenter.screen.concreteweather

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.portraitScreen.WeatherPresenterPortraitScreen
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.widescreen.WeatherPresenterWideScreen

@Composable
fun WeatherPresenterScreen(isWideScreen: Boolean, weather: Weather, onClick: (String) -> Unit) {
    if (isWideScreen)
        WeatherPresenterWideScreen(weather = weather) { pinStatus ->
            onClick(pinStatus)
        }
    else WeatherPresenterPortraitScreen(weather = weather) { pinStatus ->
        onClick(pinStatus)
    }

    Log.d("SERVICE_TAG_WID", isWideScreen.toString())
}