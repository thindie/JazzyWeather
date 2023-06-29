package com.example.thindie.presentation.designsystem

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.thindie.presentation.designsystem.BarFiller

fun asBarFiller(imageVector: ImageVector, route: String): BarFiller {
    return object : BarFiller {
        override val icon: ImageVector = imageVector
        override val route: String = route
    }
}
