package com.example.thindie.presentation.designsystem

fun asButtonFiller(label: String, onClick: (String) -> Unit): ButtonFiller {
    return object : ButtonFiller {
        override val label: String = label
        override val onClick: (String) -> Unit = onClick
    }
}
