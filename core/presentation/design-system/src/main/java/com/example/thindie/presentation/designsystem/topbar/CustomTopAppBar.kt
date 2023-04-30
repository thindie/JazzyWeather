package com.example.thindie.presentation.designsystem.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

/**
 *
 * API for [ExpandableTopBar]
 */
interface SortTriggerAvailAble {
    @get:DrawableRes
    val icon: Int
    val title: Int
    val isSelected: Boolean
    val id: String
}

/**
 * [TopAppBarScrollBehavior] of [ExpandableTopBar]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scrollBehaviors(): TopAppBarScrollBehavior {
    return TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
}

/**
 *
 * Target [Composable] of applied [scrollBehaviors]
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableTopBar(
    title: String = "TopAppBar",
    actions: List<SortTriggerAvailAble> = emptyList(),
    behavior: TopAppBarScrollBehavior,
    onClick: (SortTriggerAvailAble) -> Unit,
) {
    val pv = PaddingValues(
        start = 8.dp,
        end = 8.dp,
        top = 25.dp,
        bottom = 2.dp
    )

    /**
     * [MediumTopAppBar] expands by listening [behavior.state.heightOffset] state
     */
    val isBarExpanded = behavior.state.heightOffset == 0.0f //todo? (
    MediumTopAppBar(
        title = { if (!isBarExpanded) Text(title, style = MaterialTheme.typography.displaySmall) },
        navigationIcon = {},
        actions = {
            if (isBarExpanded) {
                LazyRow(
                    contentPadding = pv
                ) {
                    items(actions, key = { it.id }) { action ->
                        SortIconButton(
                            vector = ImageVector.vectorResource(id = action.icon),
                            title = stringResource(id = action.title),
                            isSelected = action.isSelected,
                        ) {
                            onClick(action)
                        }
                        Spacer(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                    }
                }
            }
        },
        scrollBehavior = behavior,
    )
}

@Composable
private fun SortIconButton(
    vector: ImageVector,
    title: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(36.dp)
    ) {
        Icon(
            imageVector = vector,
            contentDescription = title,
            tint = if (isSelected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(
                    0.5f
                )
            }
        )
    }
}
