package com.example.jazzyweather.ui.composables.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun typo() = MaterialTheme.typography

@Composable
fun color() = MaterialTheme.colorScheme

fun Modifier.basicColumn(height: Dp) =
    run {
        this
            .fillMaxWidth()
            .padding(start = 8.dp, end = 12.dp)
            .height(height)
    }

fun Modifier.basicRow() =
    run {
        this
            .fillMaxWidth()
            .padding(start = 8.dp, end = 12.dp)
            .height(80.dp)
    }

fun Modifier.surfaceColor() = composed { this.background(color().surface) }
fun Modifier.onSurfaceColor() = composed { this.background(color().onSurface) }
fun Modifier.eightStartPadding() = run { this.padding(eightStartPadding) }
fun Modifier.twelweEndPadding() = run { this.padding(endTwelwePadding) }

val Al_CV = Alignment.CenterVertically
val Ar_C = Arrangement.Center
val Al_H = Alignment.CenterHorizontally

@Composable
fun String.Headline(modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = this, style = typo().headlineSmall)
}

@Composable
fun String.Body(modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = this, style = typo().bodyLarge)
}

@Composable
fun String.Label(modifier: Modifier = Modifier) {
    Text(modifier = modifier, text = this, style = typo().labelMedium)
}

val eightStartPadding = 8.dp
val endTwelwePadding = 12.dp

