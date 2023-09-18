package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse

object WeatherConcreteColors {
    val titleColors
        @Composable get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme
                .surface.TransGradientVertical(MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.colorScheme.primary.TransGradientVerticalInverse(MaterialTheme.colorScheme.surface)
        }

    val unitColors
        @Composable get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.surface.TransGradientVerticalInverse(MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.colorScheme.primary.TransGradientVertical(MaterialTheme.colorScheme.surface)
        }

    val backGroundColors
        @Composable get() = MaterialTheme.colorScheme.surface

    val positiveTemperature
        @Composable get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.surfaceVariant
        } else {
            MaterialTheme.colorScheme.secondary
        }

    val negativeTemperature
        @Composable get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.secondary
        } else {
            MaterialTheme.colorScheme.tertiary
        }

    val windValue
        @Composable get() =
            if (isSystemInDarkTheme()) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }

    val rainValue
        @Composable get() = MaterialTheme.colorScheme.onSecondaryContainer


    val neutralTemperature
        @Composable get() = if (isSystemInDarkTheme()) {
            MaterialTheme.colorScheme.surface
        } else {
            MaterialTheme.colorScheme.primary
        }


    @Composable
    fun dayIndicationColor(boolean: Boolean): Color {
        return if (boolean) {
            MaterialTheme.colorScheme.onPrimary
        } else MaterialTheme.colorScheme.primary
    }


    @Composable
    fun subTitleColor(): Color {
        return if (isSystemInDarkTheme())
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f) else MaterialTheme.colorScheme.onSurfaceVariant
    }
}