package com.example.thindie.designsystem.composables

import androidx.compose.ui.graphics.Brush

interface VisualCustomizer {
    fun getColorComponent(): Brush
    fun getShapeComponent(): Float

}