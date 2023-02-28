package com.example.jazzyweather.ui.composables.util

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    TextField(value = "", onValueChange = {})
}