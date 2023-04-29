/*
package com.example.jazzyweather.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
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
import com.example.jazzyweather.data.whatWeatherForHuman
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
                if (weatherState == null) Loading()
                else
                    DetailScreen(
                        weatherState,
                    ) { weather -> viewModel.onFavoriteAdd(weather) }
            }
            composable(route = FavoriteWeathers.route) {
                FavoriteWeathersList(
                    uiState.value.favorites,
                    uiState.value.isLoading,
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
                { updateUi(it.destination.route!!) }
            }
            composable(route = Offline.route){
                OnScreen() {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .eightStartPadding()
                            .twelveEndPadding()){
                        items(uiState.value.offLine){
                            Column(Modifier.basicDimensions()) {
                                it.place.Title()
                                it.temperature.toString().Body()
                                it.weathercode.whatWeatherForHuman().LabelBold()
                                Divider()
                            }
                         }
                    }
                }
            }
        }
    }

}

@Composable
fun Loading() {
    OnScreen {
        Box(
            Modifier
                .fillMaxWidth()
                .height(two)
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(hair),
                color = color().onSurface,
                backgroundColor = color().surface
            )
        }
    }
}

*/
