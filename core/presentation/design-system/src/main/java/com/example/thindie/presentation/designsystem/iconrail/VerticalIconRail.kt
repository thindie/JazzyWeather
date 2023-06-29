package com.example.thindie.presentation.designsystem.iconrail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.BarFiller

@Composable
fun IconRail(
    modifier: Modifier = Modifier,
    components: List<BarFiller>,
    onClick: (String) -> Unit,
    isScrollable: Boolean = false,
) {
    NavigationRail(
        modifier = modifier
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
            modifier = modifier,
            userScrollEnabled = isScrollable
        ) {
            items(components) { barfiller ->
                RailComponent(barfiller, onClick)
            }
        }
    }
}

@Composable
internal fun RailComponent(
    barFiller: BarFiller,
    onClick: (String) -> Unit,
) {
    Column() {
        IconButton(onClick = { onClick(barFiller.route) }, modifier = Modifier.size(40.dp)) {
            Icon(imageVector = barFiller.icon, contentDescription = "")
        }
    }
}
