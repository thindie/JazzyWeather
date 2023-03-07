package com.example.jazzyweather.ui.composables.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val icon: ImageVector
    val route: String

}

object Weathers : Destinations {
    override val icon: ImageVector
        get() = Icons.Default.WbSunny
    override val route: String
        get() = "Weathers"


}


object FavoriteWeathers : Destinations {
    override val icon: ImageVector
        get() = Icons.Default.Star
    override val route: String
        get() = "Favorites"

}

object Possibilities : Destinations {
    override val icon: ImageVector
        get() = Icons.Default.FilterList
    override val route: String
        get() = "Possibilities"


}

object Offline : Destinations {
    override val icon: ImageVector
        get() = Icons.Default.FilterList
    override val route: String
        get() = "Offline"


}

val weatherDestinations = listOf(Weathers, FavoriteWeathers, Possibilities, Offline)