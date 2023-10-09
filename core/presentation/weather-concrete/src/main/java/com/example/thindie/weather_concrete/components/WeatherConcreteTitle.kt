package com.example.thindie.weather_concrete.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.StringHelper
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.designsystem.composables.inputfield.InputFieldState
import com.example.thindie.designsystem.composables.inputfield.rememberInputFieldState
import com.example.thindie.presentation.R

@Composable
internal fun WeatherConcreteTitle(
    modifier: Modifier = Modifier,
    state: WeatherConcreteTitleState = rememberWeatherTitleState(),
    city: String,
    timeSunset: String,
    timeSunrise: String,
    timeZone: String,
    latitude: Double,
    longitude: Double,
    elevation: Double,
    inputFieldState: InputFieldState = rememberInputFieldState(helper = StringHelper.Line(city)),
    onEdit: (String) -> Unit,
    onRemove: (String) -> Unit,
) {

    if (state.shouldConfirmEditingTitle.value) {
        val editedTitle = inputFieldState.fieldValue.value.value
        onEdit(editedTitle)
    }


    Column(
        modifier = modifier
            .height(state.height.value)
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(WeatherConcreteColors.titleColors),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            LazyRow(modifier = modifier.padding(horizontal = 4.dp)) {
                item {
                    Text(
                        text = city,
                        style = MaterialTheme
                            .typography
                            .displaySmall
                            .copy(Color.White)
                    )
                }

            }

        }
        if (state.shouldExpand.value) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = { onRemove(city) }) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.icon_delete),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                IconButton(onClick = state::onClickEdit) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.icon_edit),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            IconTextSection(modifier = modifier, icon = R.drawable.icon_sunny, title = timeSunrise)

            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_moon_cresent,
                title = timeSunset
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_time_outline,
                title = timeZone
            )
            IconButton(onClick = state::onClickInformation) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_information),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
        }

        if (state.shouldExpand.value) {
            TitleInformation(
                latitude = latitude,
                longitude = longitude,
                elevation = elevation
            )
        }
        if (state.shouldExpand.value && state.shouldShowInputField.value) {

            WeatherTitleCorrection(
                modifier = modifier.animateContentSize(),
                state = inputFieldState,
                onClickConfirm = state::onTitleSuccessEdition
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherConcreteTitle() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        Column(
            Modifier
                .background(WeatherConcreteColors.backGroundColors)
                .fillMaxSize()
        ) {
            WeatherConcreteTitle(
                city = "Санкт-Петербург",
                timeSunset = "333",
                timeSunrise = "444",
                timeZone = "EET",
                latitude = 0.2,
                longitude = 0.3,
                elevation = 0.1,
                onRemove = { _ -> },
                onEdit = { _ -> }
            )
            DatePlanchette(days = listOf(12, 23, 62, 73, 48, 64, 25), currentDay = 5)
            LazyColumn() {
                item {
                    WeatherNamedGraph(
                        graphValues = listOf(1.0, 2.0, -8.9, 4.5, 2.0, 8.9, 4.5),
                        titlePic = R.drawable.icon_celsius,
                        positiveColor = WeatherConcreteColors.positiveTemperature,
                        negativeColor = WeatherConcreteColors.negativeTemperature
                    )
                }
                item {
                    WeatherNamedGraph(
                        graphValues = listOf(1.0, 2.0, 8.9, 4.5, 17.4, 2.4, 2.4),
                        titlePic = R.drawable.icon_windy,
                        positiveColor = WeatherConcreteColors.windValue,
                        negativeColor = WeatherConcreteColors.windValue,

                        )
                }
                item {
                    WeatherNamedGraph(
                        graphValues = listOf(0.0, 2.0, 8.9, 4.5, 0.2, 2.4, 1.1),
                        titlePic = R.drawable.icon_water_drop,
                        positiveColor = WeatherConcreteColors.rainValue,
                        negativeColor = WeatherConcreteColors.rainValue,

                        )
                }


            }


        }

    }
}


