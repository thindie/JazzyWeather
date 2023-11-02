package com.example.thindie.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun EventSnack(modifier: Modifier = Modifier, eventsState: Int) {
    if (eventsState == 0) {
        Spacer(modifier = modifier.background(Color.Transparent))
    } else {
        AnimatedVisibility(visible = eventsState != 0) {
            Snackbar(
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .clip(MaterialTheme.shapes.small),
            ) {
                AnimatedVisibility(visible = eventsState != 0) {
                    if (eventsState != 0) {
                        Text(text = stringResource(id = eventsState))
                    }
                }
            }
        }
    }
}