package com.example.thindie.designsystem.animators

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Stable
class AlphaAnimator(private val time: Int, private val minimumValue: Float) : FloatAnimator() {

    init {
        currentAnimationValue.floatValue = minimumValue
    }

    private val isSelected = mutableStateOf(false)

    override val animationTime: Int
        get() = time

    override fun animate(scope: CoroutineScope) {
        isSelected.value = !isSelected.value
        scope.launch {
            if (isSelected.value)
                currentAnimationValue.floatValue = 1f
            else currentAnimationValue.floatValue = minimumValue
        }
    }
}