package com.example.thindie.designsystem.animators

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberAlphaAnimator(time: Int, minValue: Float): AlphaAnimator {
    return remember {
        AlphaAnimator(time = time, minValue)
    }
}