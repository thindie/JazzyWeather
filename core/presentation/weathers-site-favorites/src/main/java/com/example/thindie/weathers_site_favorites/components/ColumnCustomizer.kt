package com.example.thindie.weathers_site_favorites.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.VisualCustomizer

data class ColumnCustomizer(val maxValue: Number, val selfValue: Number) : VisualCustomizer {
    @Composable
    override fun getColorComponent(): Brush {
        return Brush.verticalGradient(listOf(Color.Red, Color.White))
    }

    override fun getShapeComponent(): Float {
        return selfValue.toFloat().div(maxValue.toFloat())
    }
}