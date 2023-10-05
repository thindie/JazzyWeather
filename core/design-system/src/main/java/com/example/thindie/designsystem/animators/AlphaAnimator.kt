package com.example.thindie.designsystem.animators

import androidx.compose.runtime.Stable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Stable
class AlphaAnimator(
    private val time: Int,
    minimumValue: Float,
) : FloatAnimator() {


    init {
        currentAnimationValue.floatValue = minimumValue
    }


    override val animationTime: Int
        get() = time

    override fun animate(scope: CoroutineScope) {
        scope.launch {
            currentAnimationValue.floatValue = 1f
        }

    }


}