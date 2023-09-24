package com.example.thindie.designsystem.composables.inputfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.thindie.designsystem.StringHelper
import com.example.thindie.presentation.R

@Composable
fun InputField(modifier: Modifier = Modifier, state: InputFieldState) {
    BasicTextField(
        modifier = modifier.fillMaxWidth(state.animatedValue.value),
        value = state.fieldValue.value.value,
        onValueChange = state::onValueChange
    ) {
        state.decorationBox(it)
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_2)
fun previewInputField() {
    com.example.thindie.designsystem.theme.JazzyWeatherTheme {
        InputField(state = rememberInputFieldState(helper = StringHelper.Source(R.string.hint_searh)))
    }
}