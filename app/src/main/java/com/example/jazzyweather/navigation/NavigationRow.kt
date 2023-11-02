package com.example.jazzyweather.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.designsystem.composables.ClickAbleRow
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse

@Composable
fun NavigationRow(
    state: NavigationState,
    clickAbleContent: @Composable (Modifier) -> Unit,
    shackContent: @Composable (Modifier) -> Unit,
) {
    if (state.shouldShowNavigationBar) {
        ClickAbleRow(
            rowColor = MaterialTheme.colorScheme.surfaceTint.TransGradientVerticalInverse(),
            contentColor = MaterialTheme.colorScheme.inverseSurface,
            list = navigationAbles,
            onClick = {
                state.navigate(it)
            },
            clickAbleContent = clickAbleContent,
            shackContent = shackContent
        )
    }
}