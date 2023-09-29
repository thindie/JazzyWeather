package com.example.thindie.designsystem

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberAnimatedContent(
    xStart: Float,
    yStart: Float = xStart.plus(10f),
    duration: Int = 400,
): AnimatedContentState {
    val transition: InfiniteTransition = rememberInfiniteTransition()
    return remember {
        AnimatedContentState(transition, xStart, yStart, duration = duration)
    }
}

class AnimatedContentState(
    private val transition: InfiniteTransition,
    private val xStart: Float,
    private val yStart: Float,
    val xEnd: Float = xStart.plus(10f),
    val yEnd: Float = xEnd.plus(30f),
    private val duration: Int = 400,
) {
    val y
        @Composable get() =
            transition.animateFloat(
                initialValue = yStart, targetValue = yEnd, animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        easing = LinearOutSlowInEasing,
                        delayMillis = duration / 3
                    )
                )
            )

    val x
        @Composable get() =
            transition.animateFloat(
                initialValue = xStart, targetValue = xEnd, animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        easing = LinearOutSlowInEasing,
                        delayMillis = duration / 3
                    )
                )
            )
}