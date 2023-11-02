package com.example.thindie.location_presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherLocation


@Suppress("LongParameterList")
@Composable
internal fun LocationPickerScreen(
    modifier: Modifier = Modifier,
    onSearchReact: (String) -> Unit,
    onFocusLocation: (WeatherLocation?) -> Unit,
    onShowLocation: (ForecastAble) -> Unit,
    onRememberLocation: (WeatherLocation) -> Unit,
    fieldValue: String,
    locations: List<WeatherLocation>,
    isFocusedLocationRemembered: Boolean,
    focusedLocation: WeatherLocation?,
) {

    var shouldShowDirectCoordinatesInput by remember {
        mutableStateOf(false)
    }
    var isSelected by rememberSaveable {
        mutableStateOf<Int?>(null)
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
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        LocationsPlaceInput(
            shouldShowDirectCoordinatesInput = shouldShowDirectCoordinatesInput,
            fieldValue = fieldValue,
            notifyShouldShowCoordinates = { shouldShowDirectCoordinatesInput = it },
            notifySelection = { isSelected = it },
            onSearchReact = onSearchReact,
            shouldShowCoordinatesButton = isSelected == null
        )

        LocationsList(
            modifier = modifier,
            focusedLocation = focusedLocation,
            isFocusedLocationRemembered = isFocusedLocationRemembered,
            fieldValue = fieldValue,
            locations = locations,
            isSelected = isSelected,
            notifySelection = {
                isSelected = it
            },
            onShowLocation = onShowLocation,
            onRememberLocation = onRememberLocation,
            onFocusLocation = onFocusLocation
        )

        CoordinatesInput(
            onFocusLocation = {
                onRememberLocation(it);
                onShowLocation(it)
            },
            shouldShowDirectCoordinatesInput = shouldShowDirectCoordinatesInput,
            onDismiss = { shouldShowDirectCoordinatesInput = false })

    }
}


@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationPickerScreen() {
    JazzyWeatherTheme {

    }
}