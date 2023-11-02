package com.example.thindie.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun rememberAnimatedContent(
): LoadingOrShowContentState {
    val scope = rememberCoroutineScope()
    return LoadingOrShowContentState(scope)
}

@Stable
class LoadingOrShowContentState(private val scope: CoroutineScope) {

    var rain by mutableStateOf(rainDrops())
        private set

    init {
        produce()
    }

    private fun rainDrops() = buildList {
        repeat(6) {
            this.add(Random.nextFloat())
        }
    }


    private fun produce() {
        scope.launch {
            repeat(5) {
                rain = rainDrops()
            }
        }
    }
}