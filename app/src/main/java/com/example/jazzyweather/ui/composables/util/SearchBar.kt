package com.example.jazzyweather.ui.composables.util

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var textFieldValue by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(
        Modifier
            .basicDimens(110.dp)
            .surfaceColor(),
        horizontalArrangement = Ar_C,
        verticalAlignment = Al_CV
    ) {
        TextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            modifier = Modifier
                .basicDimens(90.dp)
                .eightStartPadding()
                .twelveEndPadding(),
            leadingIcon = {
                IconButton(onClick = { onSearch(textFieldValue); keyboardController!!.hide() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = color().onSurface
                    )
                }
            }
        )
    }
}