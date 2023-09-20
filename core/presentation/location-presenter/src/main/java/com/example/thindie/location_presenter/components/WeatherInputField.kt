package com.example.thindie.location_presenter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.inputfield.InputFieldState
import com.example.thindie.designsystem.composables.inputfield.rememberInputFieldState
import com.example.thindie.presentation.R

@Composable
internal fun WeatherInputField(modifier: Modifier = Modifier, state: InputFieldState) {
    Surface(
        color = LocationPresenterColors.backGroundColors
    ) {
        OutlinedTextField(
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .fillMaxWidth(state.animatedWidth.value),
            value = state.fieldValue.value.value,
            onValueChange = state::onValueChange,
            shape = RoundedCornerShape(20.dp),
            label = { Text(text = stringResource(id = R.string.hint_searh)) }
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
internal fun previewWeatherInputField() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        WeatherInputField(state = rememberInputFieldState())
    }
}