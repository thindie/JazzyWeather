package com.example.thindie.location_presenter.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.presentation.R

@Composable
internal fun WeatherLocationPickerUnit(
    modifier: Modifier = Modifier,
    location: WeatherLocation,
    onClickFavorite: (WeatherLocation) -> Unit,
    onDismissFavorite: (WeatherLocation) -> Unit,
    onNavigateConcrete: (WeatherLocation) -> Unit,
) {

    val isSelected = rememberSaveable {
        mutableStateOf(false)
    }

    val minValue = 0.5f
    val maxValue = 1f

    val alpha =
        animateFloatAsState(
            targetValue = if (isSelected.value) maxValue else minValue,
            label = "",
            animationSpec = tween(durationMillis = 700)
        )


    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(LocationPresenterColors.unitColors)
            .height(150.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LazyRow(
                modifier = modifier
                    .fillMaxWidth(0.8f)
            ) {
                item {
                    IconTextSection(
                        modifier = modifier
                            .padding(all = 2.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                onNavigateConcrete(location)
                            },
                        icon = R.drawable.icon_location,
                        title = location.city,
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White
                    )
                }
            }
            IconButton(onClick = {
                isSelected.value = !isSelected.value
                if (isSelected.value) {
                    onClickFavorite(location)
                } else onDismissFavorite(location)
            }) {
                Icon(
                    modifier = modifier,
                    painter = painterResource(id = R.drawable.icon_favorite),
                    contentDescription = "",
                    tint = LocationPresenterColors.starValue.copy(alpha.value)
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_population,
                title = location.population,
                color = LocationPresenterColors.populationValue.copy(alpha.value)
            )

            Column {
                IconTextSection(
                    modifier = modifier.height(14.dp),
                    icon = R.drawable.icon_latitude,
                    title = location.latitude,
                    color = LocationPresenterColors.coordinatesValue.copy(alpha.value)
                )
                IconTextSection(
                    modifier = modifier.height(14.dp),
                    icon = R.drawable.icon_longitude,
                    title = location.longitude,
                    color = LocationPresenterColors.coordinatesValue.copy(alpha.value)
                )

            }
        }
    }
}


