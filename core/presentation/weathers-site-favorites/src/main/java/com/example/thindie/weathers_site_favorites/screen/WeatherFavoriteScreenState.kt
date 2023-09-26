package com.example.thindie.weathers_site_favorites.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.designsystem.AnimatedContent
import com.example.thindie.designsystem.DeBouncer
import com.example.thindie.designsystem.rememberDeBouncer
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.weathers_site_favorites.components.WeatherFavoritesColors
import com.example.thindie.weathers_site_favorites.viewmodel.WeatherFavoritesViewModel

@Composable
fun WeatherFavoriteScreenState(
    viewModel: WeatherFavoritesViewModel = hiltViewModel(),
    deBouncer: DeBouncer = rememberDeBouncer(2500),
    onClickNavigation: (ForecastAble) -> Unit,
    onClickBack: () -> Unit,
    onClickAll: () -> Unit,
) {

    deBouncer {
        viewModel.onSelectFavoriteWeatherPlacesScreen()
    }


    val favoritesScreenState =
        viewModel
            .screenState
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

    val list = favoritesScreenState.value.list

    if (list.isEmpty()) {
        AnimatedContent(
            modifier = Modifier
                .background(WeatherFavoritesColors.unitColors)
                .fillMaxSize(),
            tint = WeatherFavoritesColors.cloudyAnimColors
        )
    } else {


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
}

