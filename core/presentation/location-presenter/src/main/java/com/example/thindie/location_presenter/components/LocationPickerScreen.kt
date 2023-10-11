package com.example.thindie.location_presenter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.domain.entities.WeatherLocation

@Suppress("LongParameterList")
@Composable
internal fun LocationPickerScreen(
    modifier: Modifier = Modifier,
    onSearchReact: (String) -> Unit,
    onFocusLocation: (WeatherLocation?) -> Unit,
    onRememberLocation: (WeatherLocation) -> Unit,
    fieldValue: String,
    locations: List<WeatherLocation>,
    isFocusedLocationRemembered: Boolean,
    focusedLocation: WeatherLocation?,
) {
    Surface()
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
        ) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                value = fieldValue,
                onValueChange = onSearchReact,
                shape = MaterialTheme.shapes.large
            )

            AnimatedVisibility(visible = focusedLocation != null) {
                if (focusedLocation != null)
                    SelectedLocationPlanchette(
                        modifier = modifier,
                        isFocusedLocationRemembered = isFocusedLocationRemembered,
                        focusedLocation = focusedLocation,
                        onRememberLocation = onRememberLocation
                    )
            }
            AnimatedVisibility(visible = fieldValue.isNotBlank()) {
                LazyColumn {
                    items(locations, key = { it.hashCode() }) { weatherLocation ->
                        LocationPickerUnit(location = weatherLocation) {
                            onFocusLocation(weatherLocation)
                        }
                    }
                }
            }

        }
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationPickerScreen() {
    JazzyWeatherTheme {

    }
}