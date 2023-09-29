package com.example.thindie.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DeBouncer(private val time: Long, private val scope: CoroutineScope) {

    private var isDebounced: Boolean = false
    operator fun invoke(foo: () -> Unit) {
        if (isDebounced) { /* ignore */
        } else {
            scope.launch {
                isDebounced = true
                foo.invoke()
                delay(time)
                isDebounced = false
            }

        }
    }
}


@Composable
fun rememberDeBouncer(time: Long = 1500): DeBouncer {
    val scope: CoroutineScope = rememberCoroutineScope()
    return remember {
        DeBouncer(time, scope)
    }
}