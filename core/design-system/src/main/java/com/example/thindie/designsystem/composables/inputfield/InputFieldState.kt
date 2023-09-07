package com.example.thindie.designsystem.composables.inputfield

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.StringHelper
import kotlinx.coroutines.CoroutineScope


open class InputFieldState(scope: CoroutineScope, helper: StringHelper) {

    private var isMaxWidth by mutableStateOf(true)
    val isCleaningButtonVisible: State<Boolean>
        get() = mutableStateOf(isMaxWidth)

    private val maxWidth = 1f
    private val lesserWidth = 0.6f

    val animatedWidth
        @Composable
        get() = animateFloatAsState(
            targetValue = if (isMaxWidth) maxWidth else lesserWidth,
            label = "",
            animationSpec = spring()
        )

    private val _fieldValue = mutableStateOf(helper)
    val fieldValue: State<StringHelper>
        get() = _fieldValue

    fun onToggleWidth() {
        isMaxWidth = !isMaxWidth
    }

    fun onClean() {
        onValueChange("")
    }

    fun onValueChange(string: String) {
        _fieldValue.value = StringHelper.Line(string)
    }


    @OptIn(ExperimentalMaterial3Api::class)
    open val decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =
        @Composable { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = _fieldValue.value.value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = MutableInteractionSource(),
                colors = textFieldColors,
                contentPadding = PaddingValues(0.dp),
                container = {
                    //OutlinedTextFieldDefaults.ContainerBox(enabled, isError, interactionSource, colors)
                },
            )
        }

    @OptIn(ExperimentalMaterial3Api::class)
    open val textFieldColors: TextFieldColors
        @Composable get() = OutlinedTextFieldDefaults.colors(
            //textColor = MaterialTheme.colorScheme.primary,
            disabledTextColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.primary,
            errorCursorColor = MaterialTheme.colorScheme.error,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
            errorLeadingIconColor = MaterialTheme.colorScheme.primary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
            errorTrailingIconColor = MaterialTheme.colorScheme.primary,
        )
}