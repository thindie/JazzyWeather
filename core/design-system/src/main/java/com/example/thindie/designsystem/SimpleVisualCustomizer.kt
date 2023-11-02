package com.example.thindie.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class SimpleVisualCustomizer(
    val initialValue: String,
    private val customizeValue: Float,
    private val positiveColor: Color,
    private val negativeColor: Color,
) : VisualCustomizer {


    @Composable
    override fun getColorComponent(): Brush {

        return if (customizeValue <= 0) Brush.verticalGradient(
            listOf(
                negativeColor.copy(alpha = 0.7F),
                negativeColor,
                negativeColor,
            )
        )
        else Brush.verticalGradient(
            listOf(
                positiveColor.copy(alpha = 0.7F),
                positiveColor,
                positiveColor,
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