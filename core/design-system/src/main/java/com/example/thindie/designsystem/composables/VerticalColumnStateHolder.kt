package com.example.thindie.designsystem.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import com.example.thindie.designsystem.VisualCustomizer

@Stable
class VerticalColumnStateHolder(
    val width: Dp,
    val customizer: VisualCustomizer,
    val textLabel: String = "",
)

@Composable
fun rememberVerticalColumnState(
    width: Dp,
    customizer: VisualCustomizer,
    textLabel: String = "",
): VerticalColumnStateHolder {
    return remember() {
        VerticalColumnStateHolder(width, customizer, textLabel)
    }
}