package com.example.thindie.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush

interface VisualCustomizer {

    @Composable
    fun getColorComponent(): Brush
    fun getShapeComponent(): Float

}