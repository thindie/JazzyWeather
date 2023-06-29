package com.example.thindie.presentation.locationchoser.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.presentation.R
import com.example.thindie.presentation.designsystem.searchbar.SearchBar
import com.example.thindie.presentation.designsystem.searchbar.SearchBarState
import com.example.thindie.presentation.designsystem.searchbar.rememberSearchBarState
import com.example.thindie.presentation.designsystem.textutil.BodyLargeBoldText
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.designsystem.theme.Shapes
import com.example.thindie.presentation.locationchoser.screen.cardunit.LocationCard
import com.example.thindie.presentation.locationchoser.viewmodel.LocationChooserViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Suppress("LongParameterList")
@Composable
fun LocationChooserScreen(
    isWideScreen: Boolean,
    searchBarState: SearchBarState = rememberSearchBarState(isWideScreen),
    searchBarTextAsState: State<String> = searchBarState.textFieldState,
    locationListState: LazyListState,
    onSelectedLocation: (String, Float, Float) -> Unit,
    viewModel: LocationChooserViewModel = hiltViewModel(),
) {
    viewModel.onReactiveSearch(searchBarTextAsState)
    val listOfPossibleLocations = viewModel
        .possiblyWeatherLocations
        .collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
        .value



    LazyColumn(state = locationListState) {
        val locationCardModifier: Modifier =
            Modifier
                .padding(start = 5.dp, end = 12.dp, top = 3.dp, bottom = 3.dp)
                .height(80.dp)
                .fillMaxWidth()
                .clip(Shapes.large)

        item {
            SearchBar(searchBarState = searchBarState, isLandScape = isWideScreen) { }
        }

        items(listOfPossibleLocations) { location ->
            if (location != null) {
                LocationCard(
                    location = location, modifier = locationCardModifier.fillMaxWidth(1f)
                ) { selectedLocation, latitude, longitude ->
                    onSelectedLocation(
                        selectedLocation,
                        latitude,
                        longitude
                    )
                }
            }
            Divider()
        }
    }
    if (locationListState.canScrollForward) {
        searchBarState.keyboardController?.hide()
    }

}




