package com.example.thindie.location_presenter.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.location_presenter.viewmodel.LocationPickerViewModel

@Composable
internal fun LocationPickerScreen(
    modifier: Modifier = Modifier,
    viewModel: LocationPickerViewModel = hiltViewModel(),
    onClickConcrete: (ForecastAble) -> Unit,
) {
    val state =
        viewModel
            .uiState
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    viewModel.onSearchReact()


}


