package com.example.jazzyweather.ui.composables.util

import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val icon: ImageVector
    val route: String
}

object Weathers: Destinations{
    override val icon: ImageVector
        get() = TODO("Not yet implemented")
    override val route: String
        get() = TODO("Not yet implemented")

}

object OfflineWeathers: Destinations{
    override val icon: ImageVector
        get() = TODO("Not yet implemented")
    override val route: String
        get() = TODO("Not yet implemented")

}

object FavoriteWeathers: Destinations{
    override val icon: ImageVector
        get() = TODO("Not yet implemented")
    override val route: String
        get() = TODO("Not yet implemented")

}

object Possibilities : Destinations{
    override val icon: ImageVector
        get() = TODO("Not yet implemented")
    override val route: String
        get() = TODO("Not yet implemented")

}