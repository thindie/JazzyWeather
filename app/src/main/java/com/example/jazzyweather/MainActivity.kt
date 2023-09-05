package com.example.jazzyweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            val isWindowSizeExpanded = checkWindowSizeIsExpanded()
            val isSystemDarkThemed = isSystemInDarkTheme()
             JazzyWeatherTheme {

            }
        }
    }


    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    internal fun checkWindowSizeIsExpanded(): Boolean {
        val windowSize = calculateWindowSizeClass(activity = this)
        return when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Expanded -> true
            WindowWidthSizeClass.Medium -> true
            else -> false
        }
    }
}
