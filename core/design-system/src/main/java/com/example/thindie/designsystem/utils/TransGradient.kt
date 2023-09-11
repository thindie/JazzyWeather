package com.example.thindie.designsystem.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Color.TransGradientVertical(): Brush {
    return Brush.verticalGradient(
        listOf(Color.Transparent, this)
    )
}

@Composable
fun Color.TransGradientVerticalInverse(): Brush {
    return Brush.verticalGradient(
        listOf(this, Color.Transparent)
    )
}