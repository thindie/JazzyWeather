package com.example.thindie.weather_concrete.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
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

    val uvValue
        @Composable
        get() = rainValue.copy(alpha = 0.5f)

    val textFieldColors: TextFieldColors
        @Composable get() = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.surface,
            disabledTextColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = MaterialTheme.colorScheme.surface,
            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
            unfocusedLabelColor = Color.Transparent,
            disabledLabelColor = Color.Transparent,
            focusedPlaceholderColor = MaterialTheme.colorScheme.surface,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimary.copy(0.4f),
            disabledPlaceholderColor = MaterialTheme.colorScheme.surface,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            focusedBorderColor = MaterialTheme.colorScheme.primary.copy(0.2f) ,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary.copy(0.3f),
            disabledBorderColor = MaterialTheme.colorScheme.surface,

            )

}