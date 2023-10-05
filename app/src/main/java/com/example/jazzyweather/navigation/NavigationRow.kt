package com.example.jazzyweather.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.composables.ClickAbleRow
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse

@Composable
fun NavigationRow(state: NavigationState, onClick: () -> Unit) {
    ClickAbleRow(
        rowColor = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme
                .surface.TransGradientVertical(MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.colorScheme.primary.TransGradientVerticalInverse(MaterialTheme.colorScheme.surface)
        },
        contentColor = Color.White,
        list = navigationAbles,
        onClick = {
            onClick()
            state.navigate(it)  }
    )
}