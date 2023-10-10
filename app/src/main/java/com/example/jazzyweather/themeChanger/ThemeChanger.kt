package com.example.jazzyweather.themeChanger


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

import androidx.compose.ui.res.painterResource

@Composable
fun ThemeChanger(onClickChangeTheme: () -> Unit) {
    IconButton(onClick = onClickChangeTheme) {
        Icon(
            painter = painterResource(id = com.example.thindie.presentation.R.drawable.icon_moon_cresent),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null
        )
    }
}
