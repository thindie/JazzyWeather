package com.example.jazzyweather.ui.composables.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun typo() = MaterialTheme.typography

@Composable
fun color() = MaterialTheme.colorScheme

@Composable
fun icons() = Icons.Default

fun Modifier.basicDimensions(height: Dp = containerBasicBar) =
    run {
        this
            .fillMaxWidth()
            .height(height)
            .padding(start = eightStartPadding, end = endTwelvePadding)

    }

@Composable
fun OnScreen(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(modifier = modifier.fillMaxSize(), color = color().surface) {
        content()
    }
}


fun Modifier.surfaceColor() = composed { this.background(color().surface) }
fun Modifier.onSurfaceColor() = composed { this.background(color().onSurface) }
fun Modifier.eightStartPadding() = run { this.padding(start = eightStartPadding) }
fun Modifier.twelveEndPadding() = run { this.padding(end = endTwelvePadding) }

val Al_CV = Alignment.CenterVertically
val Ar_C = Arrangement.Center
val Al_H = Alignment.CenterHorizontally

@Composable
fun String.Display(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().headlineLarge, color = color)
}

@Composable
fun String.Headline(modifier: Modifier = Modifier) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().headlineSmall)
}

@Composable
fun String.Body(modifier: Modifier = Modifier) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().bodyLarge)
}

@Composable
fun String.Label(modifier: Modifier = Modifier) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().labelMedium)
}

@Composable
fun SpacerTwelve(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(endTwelvePadding))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldColors() = TextFieldDefaults.outlinedTextFieldColors(
    textColor = color().onSurface,
    unfocusedLabelColor = color().surface,
    focusedLabelColor = color().onSurface,
    unfocusedBorderColor = color().surface,
    focusedBorderColor = color().onSurface
)


val eightStartPadding = 8.dp
val endTwelvePadding = 12.dp
val basicWideBar = 60.dp
val containerBasicBar = 80.dp
