package com.example.thindie.presentation.designsystem.searchbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow

private const val SEARCH = ""

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    isLandScape: Boolean,
    searchBarState: SearchBarState = rememberSearchBarState(isLandScape),
    onSearch: (String) -> Unit
) {

    Row(
        modifier
            .fillMaxWidth(0.7f)
            .padding(start = 8.dp, end = 12.dp, top = 8.dp, bottom = 2.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = modifier,
            shape = ShapeDefaults.ExtraLarge,
            value = searchBarState.textFieldState.value,
            onValueChange = { searchBarState.textField.value = it },
            leadingIcon = {
                /*IconButton(
                    onClick = {
                        //onSearch(searchBarState.textField.value);
                        keyboardController!!.hide(); searchBarState.textField.value =
                        SEARCH
                    },
                    modifier = modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }*/
            },
            singleLine = true,
            colors = textFieldColors()

        )
    }
}

@Composable
fun rememberSearchBarState(isLandScape: Boolean): SearchBarState {
    return remember(isLandScape) {
        SearchBarState()
    }
}


@Stable
class SearchBarState {
    @OptIn(ExperimentalComposeUiApi::class)
    val keyboardController
    @Composable get() = LocalSoftwareKeyboardController.current

    internal val textField: MutableStateFlow<String> = MutableStateFlow("")
    val textFieldState
        @Composable get() = textField.collectAsState()
}
