package com.example.thindie.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.animators.FloatAnimator
import com.example.thindie.designsystem.animators.HeightAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

val customizerFullHeight: VisualCustomizer = object : VisualCustomizer {
    @Composable
    override fun getColorComponent(): Brush {
        return Brush.verticalGradient(listOf(Color.Red, Color.Cyan))
    }

    override fun getShapeComponent(): Float {
        return 1F
    }

}

val customizerLessHeight: VisualCustomizer = object : VisualCustomizer {
    @Composable
    override fun getColorComponent(): Brush {
        return Brush.verticalGradient(listOf(Color.White, Color.Red, Color.Red))
    }

    override fun getShapeComponent(): Float {
        return 0.8f
    }

}

val fakeHeightAnimator: FloatAnimator = HeightAnimator(
    scope = CoroutineScope(Dispatchers.Main),
    animationTarget = customizerFullHeight.getShapeComponent()
)
