package com.example.thindie.designsystem.utils

import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.SimpleVisualCustomizer
import kotlinx.coroutines.CoroutineScope

fun List<Float>.toVisualCustomizersList(
    positiveColor: Color, negativeColor: Color
): List<SimpleVisualCustomizer> {
    val max = maxBy {
        it.positive()
    }.positive()

    val list = this

    return list.map {
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