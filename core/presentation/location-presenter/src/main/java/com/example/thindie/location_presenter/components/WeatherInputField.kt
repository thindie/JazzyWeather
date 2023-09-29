package com.example.thindie.location_presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.inputfield.InputFieldState
import com.example.thindie.designsystem.composables.inputfield.rememberInputFieldState
import com.example.thindie.presentation.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun WeatherInputField(modifier: Modifier = Modifier, state: InputFieldState) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = modifier
                .height(80.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(modifier = modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .onFocusChanged { state.onFocusChange(it) }
                .fillMaxWidth(state.animatedValue.value),
                value = state.fieldValue.value.value,
                onValueChange = state::onValueChange,
                shape = RoundedCornerShape(20.dp),
                label = { },
                placeholder = { Text(text = stringResource(id = R.string.hint_searh)) },
                colors = LocationPresenterColors.textFieldColors
            )

            Row(modifier = Modifier
                .clip(CircleShape)
                .clickable {
                    keyboardController?.hide();
                    focusManager.clearFocus();
                    state.onClearField()
                }
                .background(LocationPresenterColors.titleColors)
                .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_close),
                    contentDescription = null,
                    modifier = modifier.size(36.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }

        }

    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherInputField() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        WeatherInputField(state = rememberInputFieldState())
    }
}