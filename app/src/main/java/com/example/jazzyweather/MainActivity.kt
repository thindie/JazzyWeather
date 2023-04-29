package com.example.jazzyweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.thindie.core.network.WeatherApiService
import com.example.thindie.core.network.dto.WeatherHourlyDtoBuilder
import com.example.thindie.core.network.util.serviceResponseThrowOrResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var api: WeatherApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        lifecycleScope.launch {
            /*WeatherDtoBuilder {
                serviceResponseThrowOrResult {
                    api
                        .getWeather(52f, 15f, timeZone = "Europe/Moscow")
                }
            }*/
            WeatherHourlyDtoBuilder{
                serviceResponseThrowOrResult {
                    api.getHourlyWeather(52f, 15f, timeZone = "Europe/Moscow")
                }
            }
                ?.apply {
                    Log.d("SERVICE_TAG", this.weatherCode.toString())
                }
        }
    }

    /*setContent {
        JazzyWeatherTheme {
            WeatherAppUI()
        }
    }*/
}


