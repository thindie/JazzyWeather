package com.example.thindie.weathers_site_favorites.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.designsystem.LoadingOrShowContent
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weathers_site_favorites.components.WeatherHourlySection
import com.example.thindie.weathers_site_favorites.viewmodel.WeatherFavoritesViewModel
import kotlinx.coroutines.delay

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
        tint = MaterialTheme.colorScheme.tertiary,
        isLoading = favoritesScreenState.value.isLoading
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            var shouldAnimate by remember { mutableStateOf(false) }
            FloatingActionButton(
                modifier = Modifier
                    .padding(horizontal = 36.dp, vertical = 12.dp)
                    .size(24.dp),
                onClick = { shouldAnimate = true; viewModel.onRequestRefresh() }) {

                val rotationState by animateFloatAsState(
                    targetValue = if (shouldAnimate) 0f else 360f,
                    label = "",
                    animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
                )

                Icon(
                    modifier = Modifier.rotate(rotationState),
                    painter = painterResource(id = com.example.thindie.presentation.R.drawable.icon_refresh),
                    contentDescription = null
                )

                LaunchedEffect(key1 = shouldAnimate, block = { delay(1200); shouldAnimate = false })
            }
            LazyColumn(
            ) {
                items(favoritesScreenState.value.list, key = WeatherHourly::hashCode) { weatherHourly ->
                    WeatherHourlySection(
                        hourly = weatherHourly,
                        onDecodeWeatherCode = viewModel::onDecodeWeatherCode,
                        onClickConcrete = onClickNavigation,
                        currentHour = favoritesScreenState.value.currentHour
                    )
                }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }

    }
}

