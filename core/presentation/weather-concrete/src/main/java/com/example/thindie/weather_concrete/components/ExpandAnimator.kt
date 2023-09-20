package com.example.thindie.weather_concrete.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp

open class ExpandAnimator(
    animationTime: Int,
    initialHeight: Dp,
) {

    protected val _shouldExpand = mutableStateOf(false)
    private val animationSpec = tween<Dp>(durationMillis = animationTime)


    val shouldExpand: State<Boolean>
        get() = _shouldExpand

    protected val _heightValue = mutableStateOf(initialHeight)

    val height
        @Composable get() = animateDpAsState(
            targetValue = _heightValue.value,
            animationSpec = animationSpec,
            label = ""
        )
}