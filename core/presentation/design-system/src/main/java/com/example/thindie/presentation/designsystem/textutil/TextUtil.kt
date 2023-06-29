package com.example.thindie.presentation.designsystem.textutil

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.thindie.presentation.designsystem.theme.JazzyWeatherTheme

@Composable
fun String.HeadLineText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineMedium,
            color = color,
            modifier = modifier
        )
    }

@Composable
fun String.DisplayText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.displaySmall,
            color = color,
            softWrap = true,
            textAlign = TextAlign.Justify,
            modifier = modifier
        )
    }

@Composable
fun String.VariationBodyText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineSmall,
            color = color.copy(0.8f),
            softWrap = true,
            textAlign = TextAlign.Justify,
            modifier = modifier

        )
    }

@Composable
fun String.BodyLargeBoldText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = modifier
        )
    }

@Composable
fun String.BodyLargeText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.bodyLarge,
            color = color,
            modifier = modifier
        )
    }

@Composable
fun String.BodyText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.bodySmall,
            color = color,
            modifier = modifier
        )
    }

@Composable
fun String.MiniText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            modifier = modifier
        )
    }

@Composable
fun String.LabelMediumText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface
) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelMedium,
            color = color,
            modifier = modifier
        )
    }