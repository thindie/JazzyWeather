package com.example.jazzyweather

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import com.example.jazzyweather.navigation.weatherDestinations
import com.example.thindie.presentation.designsystem.asBarFiller
import com.example.thindie.presentation.designsystem.bottomnavbar.NavBottomBar
import com.example.thindie.presentation.designsystem.monosystemscreens.WindowSizeDependableContent
import com.example.thindie.presentation.designsystem.theme.Shapes
import com.example.thindie.presentation.locationchoser.routing.possiblyLocationChoseScreen
import com.example.thindie.presentation.routes.WeatherRoutes
import com.example.thindie.presentation.weatherpresenter.routing.onConcreteLocation
import com.example.thindie.presentation.weatherpresenter.routing.selectedLocationsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherApp(
    mainViewModel: MainViewModel = viewModel(),
    isSystemDarkThemed: Boolean,
    isLandScapeOrientation: Boolean,
    locationsListState: LazyListState = rememberLazyListState(),
    appState: WeatherAppState = rememberWeatherAppState(
        isWideScreen = isLandScapeOrientation,
        isDarkTheme = isSystemDarkThemed,
    )
) {
    val concreteLocationState =
        mainViewModel
            .concreteScreenLocationProperties
            .collectAsStateWithLifecycle()
    Scaffold(
        bottomBar = {
            if (appState.isShowBottomBar)
                NavBottomBar(
                    onSelectedDestination = appState::navigate,
                    onOperateCurrentContent = {},
                    actionsBarStart = weatherDestinations.map { destination ->
                        asBarFiller(destination.icon, destination.route)
                    },
                    actionBarEnd = null
                )
        },
        floatingActionButton = {
            if (locationsListState.canScrollBackward) {
                FloatingActionButton(
                    onClick = {
                        appState.weatherScope.launch {
                            locationsListState.animateScrollToItem(
                                0
                            )
                        }
                    },
                    shape = Shapes.small
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = ""
                    ) //todo(
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { values ->
        WindowSizeDependableContent(
            modifier = Modifier.padding(values),
            isLandscape = appState.shouldShowRailNavigation,
            onClick = appState::navigate,
            navigationRailPoints =
            weatherDestinations.map { destination ->
                asBarFiller(destination.icon, destination.route)
            }
        ) {
            NavHost(
                navController = appState.weatherNavHostController,
                startDestination = appState.currentScreen.route
            ) {
                onConcreteLocation(
                    fetchContract = concreteLocationState.value,
                    isWideScreen = appState.isLandScape,
                    onClickBack = appState::navigate
                )
                selectedLocationsScreen(
                    isWideScreen = appState.isLandScape,
                    onSelectedDestination = appState::navigate
                )
                possiblyLocationChoseScreen(
                    isWideScreen = appState.isLandScape,
                    onSelectedLocation = { location, latitude, longitude ->
                        mainViewModel.updateConcreteLocationProperties(
                            location,
                            latitude,
                            longitude
                        )
                        appState.navigate(WeatherRoutes.weatherConcreteLocation)
                    },
                    locationListState = locationsListState
                )
            }
        }
    }
}

