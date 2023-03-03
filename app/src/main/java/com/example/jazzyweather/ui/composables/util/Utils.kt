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
fun ountlinedCards() =
    CardDefaults.outlinedCardColors(
        contentColor = color().onSurface,
        containerColor = color().surface,
        disabledContainerColor = color().onSurface,
        disabledContentColor = color().surface
    )

@Composable
fun typo() = MaterialTheme.typography

@Composable
fun color() = MaterialTheme.colorScheme

@Composable
fun icons() = Icons.Default

fun Modifier.basicDimensions(height: Dp = eighty) =
    run {
        this
            .fillMaxWidth()
            .height(height)
            .padding(start = eight, end = twelve)

    }

@Composable
fun OnScreen(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(modifier = modifier.fillMaxSize(), color = color().surface) {
        content()
    }
}


fun Modifier.surfaceColor() = composed { this.background(color().surface) }
fun Modifier.onSurfaceColor() = composed { this.background(color().onSurface) }
fun Modifier.eightStartPadding() = run { this.padding(start = eight) }
fun Modifier.twelveEndPadding() = run { this.padding(end = twelve) }

val Al_CV = Alignment.CenterVertically
val Ar_C = Arrangement.Center
val Al_H = Alignment.CenterHorizontally

@Composable
fun String.Display(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().headlineLarge, color = color)
}

@Composable
fun String.Headline(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().headlineSmall, color = color)
}

@Composable
fun String.Body(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().bodyLarge, color = color)
}

@Composable
fun String.Label(modifier: Modifier = Modifier, color: Color = color().onSurface) {
    modifier.eightStartPadding()
    Text(modifier = modifier, text = this, style = typo().labelMedium, color = color)
}

@Composable
fun SpacerTwelve(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(twelve))
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

val no = 0.dp
val hair = Dp.Hairline
val two = 2.dp
val eight = 8.dp
val twelve = 12.dp
val thirty = 30.dp
val sixty = 60.dp
val eighty = 80.dp
val hundred = 100.dp
val extraWide = 280.dp
