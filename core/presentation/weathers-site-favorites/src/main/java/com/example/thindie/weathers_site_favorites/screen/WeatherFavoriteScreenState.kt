package com.example.thindie.weathers_site_favorites.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.weathers_site_favorites.viewmodel.WeatherFavoritesViewModel

@Composable
fun WeatherFavoriteScreenState(
    viewModel: WeatherFavoritesViewModel = hiltViewModel(),
    onClickNavigation: (ForecastAble) -> Unit,
    onClickBack: () -> Unit,
    onClickAll: () -> Unit,
) {
    viewModel.onSelectFavoriteWeatherPlacesScreen()

    val favoritesScreenState =
        viewModel
            .screenState
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)


    WeatherFavoritesScreen(
        onClickNavigation = onClickNavigation,
        onClickBack = onClickBack,
        onClickAll = onClickAll,
        list = favoritesScreenState.value.list,
        currentHour = favoritesScreenState.value.currentHour,
        decodedWeather = favoritesScreenState.value.decodedWeather,
        decodedWeatherDrawable = favoritesScreenState.value.decodedWeatherDrawable
    )
}

