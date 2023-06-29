package com.example.thindie.presentation.designsystem.searchbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.R
import com.example.thindie.presentation.designsystem.textutil.MiniText
import kotlinx.coroutines.flow.MutableStateFlow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    isLandScape: Boolean,
    searchBarState: SearchBarState = rememberSearchBarState(isLandScape),
    onSearch: (String) -> Unit,
) {

    Row(
        modifier
            .fillMaxWidth(0.9f)
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
            },
            label = { stringResource(R.string.hint_search_bar).MiniText() },
            singleLine = true,
            colors = searchBarState.textFieldColorsState
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

    @OptIn(ExperimentalMaterial3Api::class)
    val textFieldColorsState
        @Composable get() = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colorScheme.onSurface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
            containerColor = MaterialTheme.colorScheme.surface.copy(0.5f),
            cursorColor = MaterialTheme.colorScheme.onSurface,
            errorCursorColor = MaterialTheme.colorScheme.onError,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
            errorLeadingIconColor = MaterialTheme.colorScheme.onError,
            /*focusedTrailingIconColor = focusedTrailingIconColor,
            unfocusedTrailingIconColor = unfocusedTrailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            errorTrailingIconColor = errorTrailingIconColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
            errorLabelColor = errorLabelColor,
            placeholderColor = placeholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            focusedSupportingTextColor = focusedSupportingTextColor,
            unfocusedSupportingTextColor = unfocusedSupportingTextColor,
            disabledSupportingTextColor = disabledSupportingTextColor,
            errorSupportingTextColor = errorSupportingTextColor,*/
        )
}
