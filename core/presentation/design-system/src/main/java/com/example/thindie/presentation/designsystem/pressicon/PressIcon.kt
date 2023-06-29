package com.example.thindie.presentation.designsystem.pressicon

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.BarFiller

@Composable
fun PressIcon(
    modifier: Modifier = Modifier,
    barFiller: BarFiller,
    tint: Color = MaterialTheme.colorScheme.onSurface,
    onPress: (String) -> Unit,
) {
    IconButton(onClick = { onPress(barFiller.route) }, modifier = modifier.size(40.dp)) {
        Icon(
            imageVector = barFiller.icon,
            contentDescription = "",
            modifier = modifier.size(30.dp),
            tint = tint
        )
    }
}
