package com.example.thindie.designsystem.animators

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import kotlinx.coroutines.CoroutineScope


abstract class FloatAnimator  {


    protected val currentAnimationValue = mutableFloatStateOf(0F)

    abstract val animationTime: Int

    val animatedValue
        @Composable get() = animateFloatAsState(
            targetValue = currentAnimationValue.floatValue,
            label = "",
            animationSpec = tween(durationMillis = animationTime)
        )

    abstract fun animate(scope: CoroutineScope)


}