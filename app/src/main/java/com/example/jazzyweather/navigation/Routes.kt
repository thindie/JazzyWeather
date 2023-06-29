package com.example.jazzyweather.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.thindie.presentation.routes.WeatherRoutes

internal interface Destinations {
    val icon: ImageVector
    val route: String

}

object Weathers : Destinations {
    override val icon: ImageVector
        get() = Icons.Default.WbSunny
        override val route: String
        get() = WeatherRoutes.weather


}


object Possibilities : Destinations {
    override val icon: ImageVector
        get() = Icons.Default.Search
    override val route: String
        get() = WeatherRoutes.possiblyLocation


}




internal val weatherDestinations = listOf(Weathers, Possibilities)