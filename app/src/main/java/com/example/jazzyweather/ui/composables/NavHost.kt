package com.example.jazzyweather.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jazzyweather.WeatherViewModel
import com.example.jazzyweather.ui.composables.util.*

@Composable
fun WeatherNavHost(
    navController: NavHostController,
    startDestination: Destinations,
    viewModel: WeatherViewModel = hiltViewModel(),
) {


    val uiState = viewModel.viewState.collectAsStateWithLifecycle()

    val updateUi: (String) -> Unit = { renew: String ->
        mapOf(
            FavoriteWeathers.route to { viewModel.onRequestFavorites() }
        )[renew]?.invoke()
    }


    Scaffold(topBar = {
        SearchBar(onSearch = {
            viewModel.onSearch(it); navController.straightTo(
            Possibilities.route
        )
        })
    }, bottomBar = { BottomBar(onClick = { navController.straightTo(it); updateUi(it) }) }) {

        NavHost(
            navController = navController,
            startDestination = startDestination.route,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Possibilities.route) {
                PossibilitiesList({ possib ->
                    viewModel.onRequest(possib);
                    navController.straightTo(Weathers.route)
                }, uiState.value.possibility)
            }
            composable(route = Weathers.route) {
                val weatherState = uiState.value.weather
                if (weatherState == null) OnScreen {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(two)
                    ) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(hair)
                        )
                    }

                } else
                    DetailScreen(
                        weatherState,
                        { weather -> viewModel.onFavoriteAdd(weather) },
                        { place ->
                            viewModel.onFavoriteDelete(place)
                        })
            }

            composable(route = FavoriteWeathers.route) {
                FavoriteWeathersList(
                    uiState.value.favorites,
                    uiState.value.possibility,
                    onFavoriteDelete = { viewModel.onFavoriteDelete(it) },
                    onClickPossibility = {
                        viewModel.onRequest(it); navController.straightTo(
                        Weathers.route
                    )
                    },
                    onClickWeather = {
                        viewModel.onClickWeather(it); navController.straightTo(
                        Weathers.route
                    )
                    }
                )
            }
        }
    }

}


