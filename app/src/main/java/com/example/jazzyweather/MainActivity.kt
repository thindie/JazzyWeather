package com.example.jazzyweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jazzyweather.ui.theme.JazzyWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val vm: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)


        // vm.onRequest(Possibility("Калтнинитаыф", longitude = 21.5f, latitude = 54.75f))
        vm.onSearch("москва")


        setContent {
            JazzyWeatherTheme {
                val state = vm.viewState.collectAsStateWithLifecycle()
                Log.d("SERVICE_TAG", "state collected ${state.value.favorites}")
                Log.d("SERVICE_TAG", "state collected ${state.value.weather}")
                Log.d("SERVICE_TAG", "state collected ${state.value.possibility}")

            }
        }
    }
}

