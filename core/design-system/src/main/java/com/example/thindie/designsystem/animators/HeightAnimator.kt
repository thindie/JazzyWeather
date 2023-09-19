package com.example.thindie.designsystem.animators

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HeightAnimator(private val animationTarget: Float, scope: CoroutineScope, time: Int = 1000) :
    FloatAnimator() {

    init {
        animate(scope)

    }

    override val animationTime: Int = time


    override fun animate(scope: CoroutineScope) {
        scope.launch {
            delay(animationTime.toLong())
            currentAnimationValue.floatValue = animationTarget
        }
    }
}