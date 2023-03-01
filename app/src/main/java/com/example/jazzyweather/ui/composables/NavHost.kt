package com.example.jazzyweather.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.padding
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
    Log.d("SERVICE_TAG", "${uiState.value.possibility}")
    Scaffold(topBar = {
        SearchBar(onSearch = {
            viewModel.onSearch(it); navController.straightTo(
            Possibilities.route
        )
        })
    }, bottomBar = { BottomBar(onClick = { navController.straightTo(it) }) }) {

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
                uiState.value.weather?.let {
                    DetailScreen(it, { weather -> viewModel.onFavoriteAdd(weather) }, { place ->
                    viewModel.onFavoriteDelete( place )
                })
                }
            }
            composable(route = FavoriteWeathers.route) {
                FavoriteWeathersList(
                    uiState.value.favorites,
                    onFavoriteDelete = {viewModel.onFavoriteDelete(it)}
                ) { viewModel.onClickWeather(it); navController.straightTo(Weathers.route) }
            }
        }
    }

}


