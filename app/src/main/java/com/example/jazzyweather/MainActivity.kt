package com.example.jazzyweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

    }

    /*setContent {
        JazzyWeatherTheme {
            WeatherAppUI()
        }
    }*/
}


