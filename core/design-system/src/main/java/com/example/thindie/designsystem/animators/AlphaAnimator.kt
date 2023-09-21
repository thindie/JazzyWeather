package com.example.thindie.designsystem.animators

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Stable
class AlphaAnimator(
    private val time: Int,
    private val minimumValue: Float,
    isMinValue: Boolean,
) : FloatAnimator() {

    private val _isSelected = mutableStateOf(isMinValue)

    init {
        currentAnimationValue.floatValue = minimumValue
    }


    override val animationTime: Int
        get() = time

    override fun animate(scope: CoroutineScope) {
        scope.launch {
            if (_isSelected.value)
                currentAnimationValue.floatValue = 1f
            else currentAnimationValue.floatValue = minimumValue
        }
    }

    fun setIsSelected(isSelected: Boolean) {
        _isSelected.value = isSelected
    }
}