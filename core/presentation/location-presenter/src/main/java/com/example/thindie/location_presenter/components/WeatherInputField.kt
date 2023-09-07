package com.example.thindie.location_presenter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.composables.inputfield.InputFieldState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun WeatherInputField(modifier: Modifier = Modifier, state: InputFieldState) {
    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 10.dp)
            .fillMaxWidth(state.animatedWidth.value),
        value = state.fieldValue.value.value,
        onValueChange = state::onValueChange,
        shape = RoundedCornerShape(20.dp)
    )
}