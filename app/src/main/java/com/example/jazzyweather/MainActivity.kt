package com.example.jazzyweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.example.thindie.designsystem.TransparentSystemBars
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
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
                    weatherScreen = mainViewModel.destinationState.collectAsState().value
                )

            }
        }
    }
}
