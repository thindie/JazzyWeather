package com.example.thindie.designsystem.composables.inputfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.thindie.designsystem.StringHelper
import com.example.thindie.presentation.R
import kotlinx.coroutines.CoroutineScope

private val initialValue = R.string.initial_value

@Composable
fun rememberInputFieldState(
    scope: CoroutineScope = rememberCoroutineScope(),
    helper: StringHelper = StringHelper.Source(
        initialValue
    ),
): InputFieldState {
    return remember {
        InputFieldState(scope, helper)
    }
}