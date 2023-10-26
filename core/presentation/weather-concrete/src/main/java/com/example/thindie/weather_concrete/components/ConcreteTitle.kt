package com.example.thindie.weather_concrete.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.designsystem.utils.TransGradientVertical
import com.example.thindie.domain.entities.WeatherDaily
import com.example.thindie.presentation.R

@Composable
internal fun ConcreteTitle(
    modifier: Modifier = Modifier,
    weatherDaily: WeatherDaily,
    sunset: String,
    sunrise: String,
    onRememberChanges: (WeatherDaily) -> Unit,
) {
    var shouldShowAdditionalSections by remember { mutableStateOf(false) }
    var temporaryChangedTitle by remember {
        mutableStateOf(weatherDaily.place)
    }

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .fillMaxWidth()

            .background(brush = MaterialTheme.colorScheme.primaryContainer.TransGradientVertical())
            .animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = temporaryChangedTitle,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.W600),
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_sunny,
                title = sunrise,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_moon_cresent,
                title = sunset,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_time_outline,
                title = weatherDaily.timezone,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            IconButton(onClick = {
                shouldShowAdditionalSections = !shouldShowAdditionalSections
            }) {
                Icon(
                    painterResource(id = R.drawable.icon_information),
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(visible = shouldShowAdditionalSections) {
            Row(
                modifier = modifier
                    .padding(vertical = 12.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
               // horizontalArrangement = Arrangement.SpaceEvenly,
              //  verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = temporaryChangedTitle,
                    onValueChange = { temporaryChangedTitle = it },
                    shape = MaterialTheme.shapes.large,
                    placeholder = { Text(text = stringResource(R.string.text_field_name_it_yourself)) }
                )
                AnimatedVisibility(visible = temporaryChangedTitle.isNotBlank()) {
                    OutlinedButton(
                        onClick =
                        {
                            onRememberChanges(weatherDaily.copy(place = temporaryChangedTitle));
                            shouldShowAdditionalSections = false
                        },
                    ) {
                        Text(text = stringResource(R.string.text_field_ok))
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewConcreteTitle() {
    JazzyWeatherTheme {

    }
}
