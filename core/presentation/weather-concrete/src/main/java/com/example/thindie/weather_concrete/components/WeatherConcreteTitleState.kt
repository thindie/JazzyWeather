package com.example.thindie.weather_concrete.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.designsystem.animators.ExpandAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun rememberWeatherTitleState(scope: CoroutineScope = rememberCoroutineScope()): WeatherConcreteTitleState {

    return remember {
        WeatherConcreteTitleState(scope = scope)
    }
}

@Stable
internal class WeatherConcreteTitleState(
    animationTime: Int = 1000,
    private val _height: Dp = 155.dp,
    private val _expanded: Dp = 330.dp,
    private val scope: CoroutineScope,
) : ExpandAnimator(
    animationTime = animationTime,
    initialHeight = _height
) {


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