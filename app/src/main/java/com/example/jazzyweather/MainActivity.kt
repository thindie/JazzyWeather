package com.example.jazzyweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.example.compose.JazzyWeatherTheme
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.thindie.designsystem.TransparentSystemBars
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.onStart()
        setContent {
            JazzyWeatherTheme {
                TransparentSystemBars()
                WeatherApp(
                    viewModel = mainViewModel,
                    weatherScreen = WeatherScreen.LOCATION_PICKER
                )
            }
        }
    }
}
