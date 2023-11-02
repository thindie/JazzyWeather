package com.example.thindie.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Color.TransGradientVertical(color: Color = Color.Transparent): Brush {
    return Brush.verticalGradient(
        listOf(color, this)
    )
}

@Composable
fun Color.TransGradientVerticalInverse(color: Color = Color.Transparent): Brush {
    return Brush.verticalGradient(
        listOf(this, color)
    )
}

@Composable
fun Color.Gradient(color1: Color, color2: Color): Brush {
    return Brush.linearGradient(
        listOf(this, color1, color2)
    )
}