package com.example.thindie.location_presenter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.designsystem.composables.IconTextSection
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.presentation.R

@Composable
internal fun SelectedLocationPlanchette(
    modifier: Modifier = Modifier,
    isFocusedLocationRemembered: Boolean,
    focusedLocation: WeatherLocation,
    onRememberLocation: (WeatherLocation) -> Unit,
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth()
            .height(80.dp)
            .animateContentSize(),
        tonalElevation = 35.dp,
        shadowElevation = 35.dp,

        ) {
        Row(
            modifier = modifier
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            AnimatedVisibility(visible = isFocusedLocationRemembered) {
                Icon(
                    painter = painterResource(id = R.drawable.nav_icon_favorite),
                    contentDescription = null
                )
            }


            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_population,
                title = focusedLocation.population,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_latitude,
                title = focusedLocation.latitude,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconTextSection(
                modifier = modifier,
                icon = R.drawable.icon_longitude,
                title = focusedLocation.longitude,
                color = MaterialTheme.colorScheme.onSurface
            )

            OutlinedButton(onClick = { onRememberLocation(focusedLocation) }) {
                Text(text = stringResource(id = R.string.text_label_expecting))
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewSelectedLocationPlanchette() {
    JazzyWeatherTheme {

    }
}