package com.example.thindie.presentation.designsystem.bottomnavbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.BarFiller

/**
 * A Composable function that represents the bottom navigation bar of a cryptocurrency coins app.
 * This component receives several parameters, including the callback functions to handle selected destination
 * and current content operations.
 * It also receives the start and end fillers to complete the bar.
 * [onSelectedDestination]: A callback function that receives the selected destination route as a parameter
 * and is executed when the user taps on a corresponding icon.
 * [onOperateCurrentContent]: A callback function executed when the user taps on the end filler icon.
 * [actionsBarStart]: A List of BarFiller objects to fill the start part of the bottom bar.
 * [actionBarEnd]: A BarFiller object to fill the end part of the bottom bar.
 * @see BarFiller
 * @see BottomAppBar
 * @see IconButton
 * @see Icon
 * */
@Composable
fun NavBottomBar(
    onSelectedDestination: (String) -> Unit,
    onOperateCurrentContent: () -> Unit,
    actionsBarStart: List<BarFiller>,
    actionBarEnd: BarFiller? = null, // todo can it be interchangeable and migrationable?
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 8.dp
    ) {
        LazyRow() {
            items(actionsBarStart) { screen ->
                MoveTo(screen) { onSelectedDestination(screen.route) }
            }
        }
        Spacer(
            modifier = modifier.weight(1f)
        )
        if (actionBarEnd != null) {
            MoveTo(screens = actionBarEnd) { onOperateCurrentContent() }
        }

    }
}

@Composable
internal fun MoveTo(screens: BarFiller, onClick: (String) -> Unit) {
    Column {
        IconButton(onClick = { onClick(screens.route) }) {
            Icon(imageVector = screens.icon, contentDescription = screens.route)
        }
    }
}
