package com.example.thindie.weathers_site_favorites.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse

typealias theme = MaterialTheme

object WeatherFavoritesColors {
    val titleColors
        @Composable get() = if (isSystemInDarkTheme()) {
            theme
                .colorScheme
                .surface.TransGradientVertical(theme.colorScheme.primary)
        } else {
            theme.colorScheme.primary.TransGradientVerticalInverse(theme.colorScheme.surface)
        }

    val unitColors
        @Composable get() = if (isSystemInDarkTheme()) {
            theme.colorScheme.surface.TransGradientVerticalInverse(theme.colorScheme.primary)
        } else {
            theme.colorScheme.primary.TransGradientVertical(theme.colorScheme.surface)
        }

    val backGroundColors
        @Composable get() = theme.colorScheme.surface

    private val positiveTemperature
        @Composable get() = if (isSystemInDarkTheme()) {
            theme.colorScheme.surfaceVariant
        } else {
            theme.colorScheme.secondary
        }

    private val negativeTemperature
        @Composable get() = if (isSystemInDarkTheme()) {
            theme.colorScheme.secondary
        } else {
            theme.colorScheme.primary
        }

    private val neutralTemperature
        @Composable get() = if (isSystemInDarkTheme()) {
            theme.colorScheme.surface
        } else {
            theme.colorScheme.primary
        }

    @Composable
    fun temperatureColor(temperature: Double): Color {
        return when (temperature) {
            in 0.0..100.00 -> {
                positiveTemperature
            }

            in -100.00..-0.01 -> {
                negativeTemperature
            }

            else -> {
                neutralTemperature
            }
        }
    }

    @Composable
    fun precipitationClearColor(): Color {
        return if (isSystemInDarkTheme()) theme.colorScheme.tertiary.copy(alpha = 0.7f) else theme.colorScheme.surfaceVariant
    }

    @Composable
    fun windIndicationsColor(): Color {
        return if (isSystemInDarkTheme()) theme.colorScheme.onSurface.copy(alpha = 0.7f) else theme.colorScheme.onSurfaceVariant
    }
}