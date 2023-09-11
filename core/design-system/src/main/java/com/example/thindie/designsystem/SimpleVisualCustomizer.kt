package com.example.thindie.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.animators.FloatAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimpleVisualCustomizer(
    val initialValue: String,
    private val customizeValue: Float,
    private val positiveColor: Color,
    private val negativeColor: Color,
    coroutineScope: CoroutineScope,
    private val time: Int = 1000,
) : FloatAnimator(), VisualCustomizer {


    init {
        animate(scope = coroutineScope)
    }

    override val animationTime: Int
        get() = time

    override fun animate(scope: CoroutineScope) {
        scope.launch {
            delay(time.toLong())
            currentAnimationValue.floatValue = getShapeComponent()
        }
    }

    @Composable
    override fun getColorComponent(): Brush {

        return if (customizeValue <= 0) Brush.verticalGradient(
            listOf(
                negativeColor.copy(alpha = 0.5F),
                negativeColor,
                negativeColor,
                negativeColor.copy(alpha = 0.5F),
            )
        )
        else Brush.verticalGradient(
            listOf(
                positiveColor.copy(alpha = 0.5F),
                positiveColor,
                positiveColor,
                positiveColor.copy(alpha = 0.5F),
            )
        )
    }

    override fun getShapeComponent(): Float {
        return customizeValue
            .positive()
            .notFullSize()
    }

    private fun Float.positive(): Float {
        val some = this
        return if (this <= 0) some * -1f else some
    }

    private fun Float.notFullSize(): Float = this * 0.85F
}