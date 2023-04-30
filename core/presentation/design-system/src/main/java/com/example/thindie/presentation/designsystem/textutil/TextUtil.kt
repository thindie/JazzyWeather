package com.example.thindie.presentation.designsystem.textutil

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.thindie.presentation.designsystem.theme.JazzyWeatherTheme

@Composable
fun String.HeadLineText(modifier: Modifier = Modifier) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

@Composable
fun String.HeadLineTextNews(modifier: Modifier = Modifier) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.surfaceTint.copy(0.5f),
            softWrap = true,
            textAlign = TextAlign.Justify,
            modifier = modifier
        )
    }

@Composable
fun String.NewsText(modifier: Modifier = Modifier) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(0.8f),
            softWrap = true,
            textAlign = TextAlign.Justify,
            modifier = modifier

        )
    }

@Composable
fun String.BodyText(modifier: Modifier = Modifier) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

@Composable
fun String.MiniText(modifier: Modifier = Modifier) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }

@Composable
fun String.MediumText(modifier: Modifier = Modifier) =
    JazzyWeatherTheme() {
        Text(
            this,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier
        )
    }