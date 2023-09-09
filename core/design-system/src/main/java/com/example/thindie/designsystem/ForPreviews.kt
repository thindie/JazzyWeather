package com.example.thindie.designsystem

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.composables.VisualCustomizer

val customizerFullHeight: VisualCustomizer = object : VisualCustomizer {
    override fun getColorComponent(): Brush {
        return Brush.verticalGradient(listOf(Color.Red,  Color.Cyan))
    }

    override fun getShapeComponent(): Float {
        return 1F
    }

}

val customizerLessHeight: VisualCustomizer = object : VisualCustomizer {
    override fun getColorComponent(): Brush {
        return Brush.verticalGradient(listOf(Color.Red, Color.Cyan))
    }

    override fun getShapeComponent(): Float {
        return 0.8f
    }

}