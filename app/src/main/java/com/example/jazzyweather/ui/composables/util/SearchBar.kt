package com.example.jazzyweather.ui.composables.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.example.thindie.wantmoex.R

private const val SEARCH = ""

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(onSearch: (String) -> Unit) {

    var textFieldValue by remember { mutableStateOf(SEARCH) }
    val keyboardController = LocalSoftwareKeyboardController.current
    OnScreen(
        Modifier
            .fillMaxWidth()
            .height(eighty)

    ) {
        Row(
            Modifier
                .basicDimensions(eighty)
                .surfaceColor(),
            horizontalArrangement = Ar_C,
            verticalAlignment = Al_CV
        ) {

            OutlinedTextField(
                value = textFieldValue,
                shape = ShapeDefaults.ExtraLarge,
                label = { SearchBarLabel() },
                onValueChange = { textFieldValue = it },
                modifier = Modifier
                    .basicDimensions(sixty)
                    .eightStartPadding()
                    .twelveEndPadding(),
                leadingIcon = {
                    IconButton(
                        onClick = {
                            onSearch(textFieldValue); keyboardController!!.hide(); textFieldValue =
                            SEARCH
                        },
                        modifier = Modifier.eightStartPadding()
                    ) {
                        Icon(
                            imageVector = icons().Search,
                            contentDescription = null,
                            tint = color().onSurface
                        )
                    }
                },
                singleLine = true,
                colors = textFieldColors()
            )

        }
    }
}

@Composable
private fun SearchBarLabel() {
    stringResource(id = R.string.search_label).Label(color = color().onSurface.copy(0.5f))
}