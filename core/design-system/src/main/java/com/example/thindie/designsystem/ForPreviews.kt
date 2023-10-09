package com.example.thindie.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

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




data class A(val simpleRoute: String, val simpleRes: Int) : NavigationAble {
    override fun getRoute(): String {
        return simpleRoute
    }

    override fun getVision(): Int {
        return simpleRes
    }
}

val back = A("", com.example.thindie.presentation.R.drawable.nav_icon_arrow)
val search = A("", com.example.thindie.presentation.R.drawable.nav_icon_search)
val favs = A("", com.example.thindie.presentation.R.drawable.nav_icon_favorite)

val navList = listOf(back, search, favs)