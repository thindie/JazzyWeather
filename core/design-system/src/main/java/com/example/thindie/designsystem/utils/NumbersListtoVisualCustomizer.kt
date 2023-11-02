package com.example.thindie.designsystem.utils

import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.SimpleVisualCustomizer

fun List<Number>.toVisualCustomizersList(
    positiveColor: Color, negativeColor: Color,
): List<SimpleVisualCustomizer> {

    val currentValue = this.map { it.toFloat() }

    val max = currentValue.maxBy {
        it.positive()
    }.positive()



    return currentValue.map {
        SimpleVisualCustomizer(
            initialValue = it.toString(),
            customizeValue = it.div(max),
            positiveColor = positiveColor,
            negativeColor = negativeColor,
        )
    }
}

private fun Float.positive(): Float {
    val some = this
    return if (this <= 0) some * -1f else some
}