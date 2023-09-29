package com.example.thindie.designsystem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
inline fun <T, R> LazyListScope.itemsMap(
    map : Map<T, R>,
    noinline key: ((item: R) -> Any)? = null,
    noinline contentType: (item: R) -> Any? = { null },
    crossinline headerContent: @Composable LazyItemScope.(item: T) -> Unit,
    crossinline itemContent: @Composable LazyItemScope.(item: R) -> Unit,
) {


    map.forEach {
        stickyHeader { headerContent(it.key) }
        val list: List<R> = map.values.toList()
        items(
            count = map.values.size,
            key = if (key != null) { index: Int -> key(list[index]) } else null,
            contentType = { index: Int -> contentType(list[index]) }
        ) {
            itemContent(list[it])
        }
    }
}