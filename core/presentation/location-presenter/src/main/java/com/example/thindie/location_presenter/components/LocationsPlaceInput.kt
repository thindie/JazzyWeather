package com.example.thindie.location_presenter.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.theme.JazzyWeatherTheme
import com.example.thindie.presentation.R

@Composable
internal fun LocationsPlaceInput(
    modifier: Modifier = Modifier,
    shouldShowDirectCoordinatesInput: Boolean,
    fieldValue: String,
    shouldShowCoordinatesButton: Boolean,
    notifyShouldShowCoordinates: (Boolean) -> Unit,
    notifySelection: (Int?) -> Unit,
    onSearchReact: (String) -> Unit,
) {
    AnimatedVisibility(
        visible = !shouldShowDirectCoordinatesInput, modifier = modifier.padding(vertical = 12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            OutlinedTextField(modifier = modifier.padding(horizontal = 8.dp),
                value = fieldValue,
                onValueChange = { onSearchReact(it); notifySelection(null) },
                shape = MaterialTheme.shapes.large,
                placeholder = { Text(text = stringResource(id = R.string.hint_input_field_edit)) },
                trailingIcon = {
                    AnimatedVisibility(visible = fieldValue.isNotBlank()) {
                        IconButton(
                            onClick = { onSearchReact(""); notifySelection(null) },
                            modifier = modifier.padding(horizontal = 8.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_cancel),
                                contentDescription = null
                            )
                        }
                    }
                })
            AnimatedVisibility(visible = shouldShowCoordinatesButton) {
                Spacer(modifier = Modifier.height(40.dp))
                Button(onClick = {
                    notifyShouldShowCoordinates(true)
                    notifySelection(null)
                    onSearchReact("")
                }) {
                    Text(
                        text = stringResource(
                            id = R.string.text_field_button_know
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewLocationsPlaceInput() {
    JazzyWeatherTheme {

    }
}