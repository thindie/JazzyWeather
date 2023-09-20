package com.example.thindie.weather_concrete.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

@Composable
internal fun rememberOperationsPlanchetteState(scope: CoroutineScope = rememberCoroutineScope())
        : OperationsPlanchetteState {
    return remember() {
        OperationsPlanchetteState(coroutineScope = scope)
    }
}

@Stable
internal class OperationsPlanchetteState(
    animationTime: Int = 1000,
    private val _height: Dp = 80.dp,
    private val _expanded: Dp = 200.dp,
    coroutineScope: CoroutineScope,
) : ExpandAnimator(
    animationTime = animationTime,
    initialHeight = _height
) {


    private val _actionState = mutableStateOf(Action.NONE)
    val actionState: State<Action>
        get() = _actionState

    val content
        @Composable get() = when (actionState.value) {
            Action.NONE -> {}
            Action.EDIT -> {}
            Action.DELETE -> {}
        }

    fun onClickSomeAction(action: Action) {
        _shouldExpand.value = true
        _heightValue.value = _expanded
    }

    fun onDecideAction() {
        _shouldExpand.value = false
        _heightValue.value = _height
    }

    enum class Action {
        DELETE, EDIT, NONE
    }

}