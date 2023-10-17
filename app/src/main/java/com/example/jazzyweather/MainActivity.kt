package com.example.jazzyweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.jazzyweather.navigation.rememberNavigationState
import com.example.jazzyweather.themeChanger.ThemeChanger
import com.example.thindie.designsystem.TransparentSystemBars
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.onStart()
        setContent {
            val darken = isSystemInDarkTheme()
            val navigationState = rememberNavigationState()
            val isSystemInDark = rememberSaveable() {
                mutableStateOf(darken)
            }
            JazzyWeatherTheme(useDarkTheme = isSystemInDark.value) {
                TransparentSystemBars(isSystemInDark.value)
                WeatherApp(
                    viewModel = mainViewModel,
                    weatherScreen = WeatherScreen.LOCATION_PICKER,
                    state = navigationState,
                    themeChanger = {
                        ThemeChanger {
                            isSystemInDark.value = !isSystemInDark.value
                        }
                    }
                )
            }
        }
    }
}
