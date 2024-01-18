package com.example.jazzyweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.jazzyweather.navigation.rememberNavigationState
import com.example.jazzyweather.themeChanger.ThemeChanger
import com.example.thindie.designsystem.JazzyLandingScreen
import com.example.thindie.designsystem.TransparentSystemBars
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val currentScreen = mutableStateOf(WeatherScreen.LOCATION_PICKER)

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            mainViewModel.destinationState
                .onEach { currentScreen.value = it }
                .launchIn(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            var isFirstLaunch by rememberSaveable {
                mutableStateOf(true)
            }
            val darken = isSystemInDarkTheme()
            val navigationState = rememberNavigationState()
            val isSystemInDark = rememberSaveable() {
                mutableStateOf(darken)
            }

            LaunchedEffect(true) {
                if (isFirstLaunch) {
                    delay(mainViewModel.hottingTime)
                    isFirstLaunch = false
                }
            }

            JazzyWeatherTheme(useDarkTheme = isSystemInDark.value) {
                if (!isFirstLaunch) {
                    TransparentSystemBars(isSystemInDark.value)
                    WeatherApp(
                        viewModel = mainViewModel,
                        weatherScreen = currentScreen.value,
                        state = navigationState,
                        themeChanger = {
                            ThemeChanger(modifier = it, onClickChangeTheme = {
                                isSystemInDark.value = !isSystemInDark.value
                            })
                        }
                    )
                } else {
                    JazzyLandingScreen(duration = mainViewModel.hottingTime.toInt())
                }
            }
        }
    }
}
