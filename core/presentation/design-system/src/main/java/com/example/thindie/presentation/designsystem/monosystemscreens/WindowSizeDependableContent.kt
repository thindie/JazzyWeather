package com.example.thindie.presentation.designsystem.monosystemscreens

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thindie.presentation.designsystem.BarFiller
import com.example.thindie.presentation.designsystem.iconrail.IconRail

@Suppress("LongParameterList")
@Composable
fun WindowSizeDependableContent(
    modifier: Modifier = Modifier,
    navigationRailPoints: List<BarFiller>,
    isLandscape: Boolean,
    onClick: (String) -> Unit,
    onClickSomeOperation: () -> Unit = {},
    navHost: @Composable () -> Unit,
) {
    Row(modifier = modifier) {
        if (isLandscape) {
            IconRail(
                components = navigationRailPoints,
                onClick = onClick
            )
        }
        navHost()
    }
}