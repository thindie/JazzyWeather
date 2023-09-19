package com.example.thindie.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thindie.presentation.R


private val black = Font(R.font.inter_black)
private val regular = Font(R.font.inter_regular)
private val bold = Font(R.font.inter_bold)
private val extraBold = Font(R.font.inter_extra_bold)

private val JazzyWeatherFonts = FontFamily(fonts = listOf(black, regular, bold, extraBold))

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
