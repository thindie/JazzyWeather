package com.example.thindie.weather_concrete.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun rememberWeatherTitleState(scope: CoroutineScope = rememberCoroutineScope()): WeatherConcreteTitleState {

    return remember {
        WeatherConcreteTitleState(scope)
    }
}

@Stable
internal class WeatherConcreteTitleState(private val scope: CoroutineScope) {
    private val animationTime = 1000
    private val _shouldExpand = mutableStateOf(false)
    private val animationSpec = tween<Dp>(durationMillis = animationTime)
    private val _height = 155.dp
    private val _expanded = 330.dp

    val shouldExpand: State<Boolean>
        get() = _shouldExpand

    private val _heightValue = mutableStateOf(_height)

    val height
        @Composable get() = animateDpAsState(
            targetValue = _heightValue.value,
            animationSpec = animationSpec,
            label = ""
        )

    fun onClickInformation() {
        scope.launch {
            if (_shouldExpand.value) {
                _shouldExpand.value = !_shouldExpand.value
                _heightValue.value = _height
            } else {
                _heightValue.value = _expanded
                _shouldExpand.value = !_shouldExpand.value
            }
        }


    }
}