package com.example.thindie.location_presenter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.JazzyWeatherTheme
import com.example.thindie.domain.entities.WeatherLocation
import com.example.thindie.presentation.R

@Composable
internal fun CoordinatesInput(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onFocusLocation: (WeatherLocation) -> Unit,
) {
    val latitudeState = remember {
        mutableStateOf("")
    }

    val longitudeState = remember {
        mutableStateOf("")
    }

    val titleState = remember {
        mutableStateOf("")
    }




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.text_field_place_new, titleState.value),
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            shape = MaterialTheme.shapes.medium,
            value = latitudeState.value,
            onValueChange = {
                latitudeState.value = it
            },
            label = { Text(text = stringResource(id = R.string.text_field_latitude)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            trailingIcon = {
                AnimatedVisibility(visible = latitudeState.value.isNotBlank()) {
                    IconButton(onClick = { latitudeState.value = "" }) {
                        Icon(
                            painterResource(id = R.drawable.icon_cancel),
                            contentDescription = null
                        )
                    }
                }
            }
        )

        OutlinedTextField(
            shape = MaterialTheme.shapes.medium,
            value = longitudeState.value.toString(),
            onValueChange = {
                longitudeState.value = it
            },
            label = { Text(text = stringResource(id = R.string.text_field_longitude)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            trailingIcon = {
                AnimatedVisibility(visible = longitudeState.value.isNotBlank()) {
                    IconButton(onClick = { longitudeState.value = "" }) {
                        Icon(
                            painterResource(id = R.drawable.icon_cancel),
                            contentDescription = null
                        )
                    }
                }
            }
        )


        OutlinedTextField(
            shape = MaterialTheme.shapes.medium,
            value = titleState.value,
            onValueChange = {
                titleState.value = it
            },
            label = { Text(text = stringResource(id = R.string.text_field_place)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            trailingIcon = {
                AnimatedVisibility(visible = titleState.value.isNotBlank()) {
                    IconButton(onClick = { titleState.value = "" }) {
                        Icon(
                            painterResource(id = R.drawable.icon_cancel),
                            contentDescription = null
                        )
                    }
                }
            }
        )
        Spacer(modifier = modifier.height(40.dp))

        Button(
            onClick = {
                if (latitudeState.value.isNotBlank()
                    && longitudeState.value.isNotBlank()
                    && titleState.value.isNotBlank()
                ) {
                    onFocusLocation(
                        WeatherLocation(
                            adminName = "",
                            capital = "",
                            city = titleState.value,
                            country = "",
                            iso2 = "",
                            latitude = latitudeState.value,
                            longitude = longitudeState.value,
                            population = "",
                            populationProper = "",
                            timezone = ""
                        )
                    )
                } else onDismiss()
            }
        ) {
            AnimatedVisibility(
                visible = latitudeState.value.isNotBlank()
                        && longitudeState.value.isNotBlank()
                        && titleState.value.isNotBlank()
            ) {
                Text(text = stringResource(id = R.string.button_text_remember))
            }

            AnimatedVisibility(
                visible = latitudeState.value.isBlank()
                        || longitudeState.value.isBlank()
                        || titleState.value.isBlank()
            ) {
                Text(text = stringResource(id = R.string.text_field_button_know_not))
            }
        }
    }

}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewCoordinatesInput() {
    JazzyWeatherTheme {

    }
}