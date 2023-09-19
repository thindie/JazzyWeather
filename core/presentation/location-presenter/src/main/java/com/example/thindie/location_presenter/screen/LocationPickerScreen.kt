package com.example.thindie.location_presenter.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.designsystem.composables.inputfield.InputFieldState
import com.example.thindie.designsystem.composables.inputfield.rememberInputFieldState
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.location_presenter.components.WeatherInputField
import com.example.thindie.location_presenter.components.WeatherLocationPickerUnit
import com.example.thindie.location_presenter.viewmodel.LocationPickerViewModel

@Composable
internal fun LocationPickerScreen(
    modifier: Modifier = Modifier,
    inputFieldState: InputFieldState = rememberInputFieldState(),
    viewModel: LocationPickerViewModel = hiltViewModel(),
) {
    val state =
        viewModel.uiState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    viewModel.onSearchReact(inputFieldState.fieldValue.value.value)
    Scaffold(topBar = { WeatherInputField(state = inputFieldState) }) {
        LazyColumn(modifier = modifier.padding(it)) {
            items(state.value.locationsList) { location ->
                WeatherLocationPickerUnit(
                    location = location,
                    onClickFavorite = viewModel::onClickAddFavorites
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationPickerScreen() {
    JazzyWeatherTheme {
        WeatherInputField(state = rememberInputFieldState())
    }
}

