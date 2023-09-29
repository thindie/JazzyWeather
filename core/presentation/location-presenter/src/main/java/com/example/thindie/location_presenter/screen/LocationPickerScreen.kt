package com.example.thindie.location_presenter.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.designsystem.composables.inputfield.InputFieldState
import com.example.thindie.designsystem.composables.inputfield.rememberInputFieldState
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.location_presenter.components.EmptySearchContent
import com.example.thindie.location_presenter.components.LocationPresenterColors
import com.example.thindie.location_presenter.components.WeatherInputField
import com.example.thindie.location_presenter.components.WeatherLocationPickerUnit
import com.example.thindie.location_presenter.viewmodel.LocationPickerViewModel

@Composable
internal fun LocationPickerScreen(
    modifier: Modifier = Modifier,
    inputFieldState: InputFieldState = rememberInputFieldState(),
    viewModel: LocationPickerViewModel = hiltViewModel(),
    onClickConcrete: (ForecastAble) -> Unit,
) {
    val state =
        viewModel.uiState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    viewModel.onSearchReact(inputFieldState.fieldValue.value.value)
    Column(
        modifier = modifier
            .background(LocationPresenterColors.unitColors)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        WeatherInputField(state = inputFieldState)
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .height(40.dp)
        )

        if (state.value.locationsList.isEmpty()) {
            EmptySearchContent()
        } else
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(state.value.locationsList, key = { it.latitude + it.longitude }) { location ->

                    WeatherLocationPickerUnit(
                        location = location,
                        onClickFavorite = {
                            viewModel.onClickAddFavorites(it)
                        },
                        onDismissFavorite = {
                            viewModel.onDeleteWeatherSite(it.city)
                        },
                        onNavigateConcrete = onClickConcrete
                    )
                }
            }
    }
}


