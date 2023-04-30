package com.example.thindie.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thindie.jazzyweather.R


private val light = Font(R.font.sf_pro, FontWeight.W300)
private val regular = Font(R.font.sf_pro, FontWeight.W400)
private val medium = Font(R.font.sf_pro, FontWeight.W500)
private val semibold = Font(R.font.sf_pro, FontWeight.W600)

private val JazzyWeatherFonts = FontFamily(fonts = listOf(light, regular, medium, semibold))

val JazzyWeatherTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W300,
        fontSize = 96.sp
    ),
    displayMedium = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W400,
        fontSize = 60.sp
    ),
    displaySmall = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W600,
        fontSize = 34.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    titleSmall = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = JazzyWeatherFonts,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp
    ),
)
