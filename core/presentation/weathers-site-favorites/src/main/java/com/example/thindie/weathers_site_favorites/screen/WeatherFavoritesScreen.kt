package com.example.thindie.weathers_site_favorites.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.weathers_site_favorites.components.WeatherFavoriteHeader
import com.example.thindie.weathers_site_favorites.components.WeatherFavoriteHourlyUnit
import com.example.thindie.weathers_site_favorites.components.WeatherFavoritesColors
import com.example.thindie.weathers_site_favorites.viewmodel.WeatherFavoritesViewModel

@Composable
internal fun WeatherFavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherFavoritesViewModel = hiltViewModel(),
) {
    viewModel.onSelectFavoriteWeatherPlacesScreen()
    val favoritesScreenState =
        viewModel.screenState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WeatherFavoritesColors.backGroundColors)
    ) {
        LazyColumn() {
            items(favoritesScreenState.value.list) { weather ->
                WeatherFavoriteHeader(
                    city = weather.place,
                    celsium = weather.apparentTemperature.first().toString(),
                    sunrise = "A",
                    sunset = "B",
                    precipitation = "C",
                    contextDependableSurfaceColor = WeatherFavoritesColors.titleColors
                )
                LazyRow() {
                    items(weather.getHourlyForecast()) { hourlyWeather ->
                        WeatherFavoriteHourlyUnit(
                            contextDependableSurfaceColor = WeatherFavoritesColors.unitColors,
                            time = hourlyWeather.time,
                            celsium = hourlyWeather.apparentTemperature,
                            windSpeed = hourlyWeather.windSpeed10m.toString(),
                            precipitation = hourlyWeather.precipitation
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherFavoritesScreen() {
    JazzyWeatherTheme {

    }
}

