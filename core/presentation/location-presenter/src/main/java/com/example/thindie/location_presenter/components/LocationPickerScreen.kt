package com.example.thindie.location_presenter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.designsystem.utils.TransGradientVerticalInverse
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.presentation.R

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

    val shouldShowDirectCoordinatesInput = remember {
        mutableStateOf(false)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                MaterialTheme
                    .colorScheme
                    .surfaceTint
                    .TransGradientVertical()
            )
            .padding(horizontal = 8.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedVisibility(visible = !shouldShowDirectCoordinatesInput.value) {
            OutlinedTextField(
                modifier = modifier
                    .padding(horizontal = 8.dp),
                value = fieldValue,
                onValueChange = onSearchReact,
                shape = MaterialTheme.shapes.large,
                placeholder = { Text(text = stringResource(id = R.string.hint_input_field_edit)) },
                trailingIcon = {
                    AnimatedVisibility(visible = fieldValue.isNotBlank()) {
                        IconButton(onClick = { onSearchReact("") }) {
                            Icon(
                                painterResource(id = R.drawable.icon_cancel),
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }


        AnimatedVisibility(visible = focusedLocation != null) {
            if (focusedLocation != null) SelectedLocationPlanchette(
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
        AnimatedVisibility(visible = shouldShowDirectCoordinatesInput.value) {
            CoordinatesInput(
                onFocusLocation = onFocusLocation,
                onDismiss = { shouldShowDirectCoordinatesInput.value = false })
        }

        Button(onClick = {
            shouldShowDirectCoordinatesInput.value = true
            onSearchReact("")
        }) {
            Text(
                text = stringResource(
                    id = if (!shouldShowDirectCoordinatesInput.value) R.string.text_field_button_know
                    else R.string.text_field_button_know_perhaps
                )
            )
        }
    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationPickerScreen() {
    JazzyWeatherTheme {

    }
}