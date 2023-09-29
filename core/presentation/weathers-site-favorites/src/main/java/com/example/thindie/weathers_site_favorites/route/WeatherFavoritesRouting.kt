package com.example.thindie.location_presenter.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.weathers_site_favorites.screen.WeatherFavoriteScreenState

internal const val all = "all"
fun NavGraphBuilder.WeatherFavorites(
    onClickNavigation: (ForecastAble) -> Unit,
    onClickBack: () -> Unit,
    onClickAll: () -> Unit,
) {
    composable(route = all) {
        WeatherFavoriteScreenState(
            onClickNavigation = onClickNavigation,
            onClickBack = onClickBack,
            onClickAll = onClickAll
        )
    }

}