package com.example.thindie.designsystem

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

open class StringHelper private constructor() {
    data class Source(@StringRes val string: Int) : StringHelper()
    data class Line(val line: String) : StringHelper()

    val value
        @Composable get() =
            when (this) {
                is Source -> {
                    stringResource(id = string)
                }

                is Line -> {
                    line
                }

                else -> {
                    ""
                }
            }
}