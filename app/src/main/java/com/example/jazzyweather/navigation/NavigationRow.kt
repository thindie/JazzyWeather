package com.example.jazzyweather.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.composables.ClickAbleRow
import com.example.thindie.designsystem.utils.TransGradientVertical

@Composable
fun NavigationRow(state: NavigationState, clickAbleContent: @Composable () -> Unit) {
    ClickAbleRow(
        rowColor = MaterialTheme.colorScheme.primary.TransGradientVertical(),
        contentColor = Color.White,
        list = navigationAbles,
        onClick = {
            state.navigate(it)
        },
        clickAbleContent = clickAbleContent
    )
}