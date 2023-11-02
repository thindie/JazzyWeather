package com.example.thindie.location_presenter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherLocation

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun LocationsList(
    modifier: Modifier = Modifier,
    focusedLocation: WeatherLocation?,
    isFocusedLocationRemembered: Boolean,
    fieldValue: String,
    locations: List<WeatherLocation>,
    isSelected: Int?,
    notifySelection: (Int) -> Unit,
    onShowLocation: (ForecastAble) -> Unit,
    onRememberLocation: (WeatherLocation) -> Unit,
    onFocusLocation: (WeatherLocation) -> Unit,

    ) {
    AnimatedVisibility(visible = fieldValue.isNotBlank()) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            stickyHeader {
                AnimatedVisibility(visible = focusedLocation != null) {
                    if (focusedLocation != null) SelectedLocationPlanchette(
                        modifier = modifier,
                        isFocusedLocationRemembered = isFocusedLocationRemembered,
                        focusedLocation = focusedLocation,
                        onRememberLocation = onRememberLocation
                    )
                }
            }
            itemsIndexed(
                locations,
                key = { _, location -> location.hashCode() }) { i, weatherLocation ->


                LocationPickerUnit(
                    location = weatherLocation,
                    onShowLocation = onShowLocation,
                    isSelected = isSelected == i,
                    onClickCard = {
                        onFocusLocation(weatherLocation)
                        notifySelection(i)
                    })
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationsList() {
    JazzyWeatherTheme {

    }
}