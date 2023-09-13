package com.example.thindie.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private fun JazzyWeatherTheme(boolean: Boolean): ColorScheme {
    return if (!boolean) LightColors else DarkColors
}

private val LightColors = lightColorScheme(
    primary = Color(0xFF5053A9),
    onPrimary = Color(0xFF1C1195),
    secondary = Color(0xFFFF0000),
    surface = Color(0xFFC9C4FE),
    tertiary = Color(0xFF557BFF),
    onSurface = Color.Black,
    surfaceVariant = Color(0xFFF58F17),
    onSurfaceVariant = Color.White,
)


private val DarkColors = darkColorScheme(
    primary = Color(0xFF5053A9),
    onPrimary = Color(0xFFC9C4FE),
    secondary = Color(0xFF557BFF),
    tertiary = Color(0xFFFBFF34),
    surface = Color(0xFF000000),
    surfaceVariant = Color(0xFFC41A1A),
    onSurface = Color.White,
    onSurfaceVariant = Color.Black
)

@Composable
fun JazzyWeatherTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit,
) {

    MaterialTheme(
        colorScheme = JazzyWeatherTheme(useDarkTheme),
        content = content,
        shapes = Shapes,
        typography = JazzyWeatherTypography
    )
}
