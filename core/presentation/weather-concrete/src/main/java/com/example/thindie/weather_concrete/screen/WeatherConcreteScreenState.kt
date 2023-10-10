package com.example.thindie.weather_concrete.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.designsystem.LoadingOrShowContent
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.weather_concrete.viewmodel.WeatherConcreteViewModel

@Composable
internal fun WeatherConcreteScreenState(
    onClickNavigation: () -> ForecastAble?,
    viewModel: WeatherConcreteViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
    onClickAll: () -> Unit,
    onRemove: () -> Unit,
    onTitleComplete: () -> Unit = onRemove,
) {
    viewModel.onLoadConcreteScreen(onClickNavigation())
    val screenState =
        viewModel
            .concreteScreenState
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    LoadingOrShowContent(
        tint = MaterialTheme.colorScheme.onPrimary,
        isLoading = screenState.value.isLoading
    ) {
        WeatherConcreteScreen(
            screenState = screenState.value,
            onEdit = {

            },
            onRemove = { viewModel.onDeleteLocation(it); onRemove() })
    }

}

