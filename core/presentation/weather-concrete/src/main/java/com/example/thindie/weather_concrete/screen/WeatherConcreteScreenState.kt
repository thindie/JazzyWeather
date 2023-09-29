package com.example.thindie.weather_concrete.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherDaily
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
    WeatherConcreteScreen(
        screenState = screenState.value,
        onEdit = {
            viewModel.onRememberChanges(screenState.value.weatherDaily.about(it));
           onTitleComplete()
        },
        onRemove = { viewModel.onDeleteLocation(it); onRemove() })
}


private fun WeatherDaily?.about(title: String): WeatherDaily {
    return requireNotNull(this?.copy(place = title))
}