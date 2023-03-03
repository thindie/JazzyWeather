package com.example.jazzyweather.ui.composables.util

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomBar(onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    BottomAppBar(modifier) {
        BottomBarElement(destinations = Weathers, onClick = onClick)
        BottomBarElement(destinations = FavoriteWeathers, onClick = onClick)
    }
}

@Composable
fun BottomBarElement(
    destinations: Destinations,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    IconButton(onClick = { onClick(destinations.route) }, modifier.eightStartPadding()) {
        Icon(
            imageVector = destinations.icon,
            contentDescription = destinations.route,
            tint = color().onSurface
        )
    }
}