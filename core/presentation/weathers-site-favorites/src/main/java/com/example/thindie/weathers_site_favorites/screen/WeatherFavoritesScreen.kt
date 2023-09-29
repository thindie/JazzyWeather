package com.example.thindie.weathers_site_favorites.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.domain.entities.ForecastAble
import com.example.thindie.domain.entities.WeatherHourly
import com.example.thindie.weathers_site_favorites.components.WeatherFavoriteHeader
import com.example.thindie.weathers_site_favorites.components.WeatherFavoriteHourlyUnit
import com.example.thindie.weathers_site_favorites.components.WeatherFavoritesColors

@Composable
internal fun WeatherFavoritesScreen(
    modifier: Modifier = Modifier,
    onClickNavigation: (ForecastAble) -> Unit,
    onClickBack: () -> Unit,
    onClickAll: () -> Unit,
    list: List<WeatherHourly>,
    currentHour: Int,
    @StringRes decodedWeather: Int,
    @DrawableRes decodedWeatherDrawable: Int,
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WeatherFavoritesColors.backGroundColors)
    ) {
        LazyColumn {

            items(list) { weather ->
                val lazyRowState: LazyListState = rememberLazyListState()
                WeatherFavoriteHeader(
                    city = weather.place,
                    celsium = weather.apparentTemperature[currentHour].toString(),
                    contextDependableSurfaceColor = WeatherFavoritesColors.titleColors,
                    weatherCurrent = decodedWeather,
                    weatherCurrentPic = decodedWeatherDrawable,
                    onClickHeader = { onClickNavigation(weather) }
                )
                LaunchedEffect(true) {
                    lazyRowState.scrollToItem(currentHour)
                }


                LazyRow(state = lazyRowState, modifier = modifier.padding(horizontal = 8.dp)) {
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

