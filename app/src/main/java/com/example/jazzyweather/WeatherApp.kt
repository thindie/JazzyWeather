package com.example.jazzyweather

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.jazzyweather.navigation.NavigationRow
import com.example.jazzyweather.navigation.NavigationState
import com.example.jazzyweather.navigation.WeatherScreen
import com.example.jazzyweather.navigation.destination
import com.example.jazzyweather.navigation.rememberNavigationState
import com.example.thindie.location_presenter.route.LocationPicker
import com.example.thindie.location_presenter.route.WeatherFavorites
import com.example.thindie.weather_concrete.route.WeatherConcrete

@Composable
fun WeatherApp(
    viewModel: MainViewModel,
    weatherScreen: WeatherScreen,
    state: NavigationState = rememberNavigationState(),
    themeChanger: @Composable () -> Unit,
) {

    Scaffold(
        bottomBar = { NavigationRow(state = state, clickAbleContent = themeChanger) }
    ) {

        val currentForecastAble = viewModel.forecastAbleState.collectAsState()
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
                onClickConcrete = { viewModel.onChoseForecastAble(it); state.concreteScreen() },
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
                onClickBack = state::back,
                onClickAll = state::favoritesScreen,
                onRemove = state::favoritesScreen,
            )
        }
    }
}
