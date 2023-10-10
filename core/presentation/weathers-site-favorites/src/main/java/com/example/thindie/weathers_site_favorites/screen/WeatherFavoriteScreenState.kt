package com.example.thindie.weathers_site_favorites.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.designsystem.LoadingOrShowContent
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

    LoadingOrShowContent(
        tint = MaterialTheme.colorScheme.surface,
        isLoading = favoritesScreenState.value.isLoading
    ) {
        LazyColumn() {
            items(favoritesScreenState.value.list) { weatherHourly ->
                ClickableText(
                    text = AnnotatedString(weatherHourly.place),
                    onClick = { onClickNavigation(weatherHourly) })

            }
        }
    }

}

