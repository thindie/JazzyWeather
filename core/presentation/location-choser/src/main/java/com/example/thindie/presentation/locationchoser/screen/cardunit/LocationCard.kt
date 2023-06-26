package com.example.thindie.presentation.locationchoser.screen.cardunit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.thindie.presentation.locationchoser.viewmodel.LocationChooserViewModel

@Composable
internal fun LocationCard(
    modifier: Modifier = Modifier,
    location: LocationChooserViewModel.Location,
    onSelectedLocation: (String, Float, Float) -> Unit
) {
    Row(modifier = modifier.clickable {
        onSelectedLocation(
            location.city,
            location.latitude.toFloatOrNull() ?: 0f,
            location.longitude.toFloatOrNull() ?: 0f  //todo(
        )
    }) {
        LocationCardStart(modifier.fillMaxWidth(0.3f), location.city, location.population)
        LocationCardBody(modifier.fillMaxWidth(0.3f), location.latitude, location.longitude)
        LocationCardEnd(modifier.fillMaxWidth(0.3f), location.adminName)
    }
}