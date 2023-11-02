package com.example.jazzyweather

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import com.example.jazzyweather.navigation.NavigationRow
import com.example.jazzyweather.navigation.NavigationState
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.jazzyweather.navigation.destination
import com.example.jazzyweather.navigation.rememberNavigationState
import com.example.thindie.designsystem.EventSnack
import com.example.thindie.location_presenter.route.LocationPicker
import com.example.thindie.location_presenter.route.WeatherFavorites
import com.example.thindie.weather_concrete.route.WeatherConcrete

@Composable
fun WeatherApp(
    viewModel: MainViewModel,

    weatherScreen: WeatherScreen,
    state: NavigationState = rememberNavigationState(),
    themeChanger: @Composable (Modifier) -> Unit,
) {

    Scaffold(

    ) {

        val currentForecastAble = viewModel.forecastAbleState.collectAsState()

        val eventsState by viewModel
            .eventsState
            .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)

        NavHost(
            modifier = Modifier
                .padding(it),
            navController = state.navHostController,
            startDestination = state
                .startDestination
                ?.destination()
                ?: weatherScreen.destination()
        ) {

            LocationPicker(
                onClickConcrete = { forecastAble ->
                    viewModel.onChoseForecastAble(forecastAble)
                    viewModel.onRequestFetch(forecastAble)
                    state.concreteScreen()
                },
            )

            WeatherFavorites(
                onClickNavigation = { viewModel.onChoseForecastAble(it); state.concreteScreen() },
                onClickBack = state::back,
                onClickAll = state::favoritesScreen,
            )
            WeatherConcrete(
                onClickNavigation = {
                    currentForecastAble.value
                },
                onRemove = state::favoritesScreen,
            )
        }
        AnimatedVisibility(
            state.shouldShowNavigationBar,
            enter = slideIn(initialOffset = { IntOffset(x = 0, y = 0) }),
            exit = fadeOut()
        ) {
            NavigationRow(state = state, clickAbleContent = themeChanger, shackContent = {
                     EventSnack(
                        modifier = it,
                        eventsState = eventsState,
                    )
             })
        }
    }
}
