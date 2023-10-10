package com.example.thindie.location_presenter.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.domain.entities.WeatherLocation

@Composable
internal fun LocationPickerScreen(
    modifier: Modifier = Modifier,
    onSearchReact: (String) -> Unit,
    onFocusLocation: (WeatherLocation?) -> Unit,
    fieldValue: String,
    locations: List<WeatherLocation>,
    focusedLocation: WeatherLocation?,
) {

    Surface()
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondary.TransGradientVertical())
        ) {
            OutlinedTextField(
                value = fieldValue,
                onValueChange = onSearchReact,
                shape = MaterialTheme.shapes.large
            )

            if (focusedLocation != null) {
                Card(
                    modifier = modifier
                        .animateContentSize()
                ) {
                    Text(text = focusedLocation.population)
                }
            }

            LazyColumn {
                items(locations, key = { it.hashCode() }) {
                    LocationPickerUnit(location = it) {
                        onFocusLocation(it)
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