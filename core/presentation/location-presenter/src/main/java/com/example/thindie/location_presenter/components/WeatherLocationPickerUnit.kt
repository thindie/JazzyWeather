package com.example.thindie.location_presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.animators.AlphaAnimator
import com.example.thindie.designsystem.animators.rememberAlphaAnimator
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.presentation.R
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun WeatherLocationPickerUnit(
    modifier: Modifier = Modifier,
    location: WeatherLocation,
    animator: AlphaAnimator = rememberAlphaAnimator(time = 700, minValue = 0.5f),
    scope: CoroutineScope = rememberCoroutineScope(),
    onClickFavorite: (WeatherLocation) -> Unit,
) {


    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(LocationPresenterColors.titleColors)
            .height(80.dp)
            .padding(horizontal = 8.dp, vertical = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LazyRow(modifier = modifier.widthIn(max = 120.dp)) {
                item {
                    IconTextSection(
                        modifier = modifier,
                        icon = R.drawable.icon_location,
                        title = location.city
                    )
                }
            }

            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_population,
                title = location.population,
                color = LocationPresenterColors.starValue.copy(animator.animatedValue.value)
            )

            Column {
                IconTextSection(
                    modifier = modifier.height(14.dp),
                    icon = R.drawable.icon_latitude,
                    title = location.latitude,
                    color = LocationPresenterColors.starValue.copy(animator.animatedValue.value)
                )
                IconTextSection(
                    modifier = modifier.height(14.dp),
                    icon = R.drawable.icon_longitude,
                    title = location.longitude,
                    color = LocationPresenterColors.starValue.copy(animator.animatedValue.value)
                )

            }
            IconButton(onClick = {
                animator.animate(scope); onClickFavorite(location)
            }) {
                Icon(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.icon_favorite),
                    contentDescription = "",
                    tint = LocationPresenterColors.starValue.copy(animator.animatedValue.value)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherLocationPickerUnit() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        Column {
            WeatherLocationPickerUnit(
                location = WeatherLocation(
                    adminName = "name",
                    capital = "capital",
                    city = "capitalcity321312312344",
                    country = "",
                    iso2 = "",
                    latitude = "1234442",
                    longitude = "4323423",
                    population = "12345333",
                    populationProper = "", timezone = "",

                    ), onClickFavorite = {}, modifier = Modifier
            )
            WeatherLocationPickerUnit(
                location = WeatherLocation(
                    adminName = "name",
                    capital = "capital",
                    city = "capitalcity",
                    country = "",
                    iso2 = "",
                    latitude = "",
                    longitude = "",
                    population = "12345",
                    populationProper = "", timezone = "",
                ), onClickFavorite = {}, modifier = Modifier
            )
        }
    }
}

