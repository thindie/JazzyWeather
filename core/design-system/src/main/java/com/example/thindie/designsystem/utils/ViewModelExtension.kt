package com.example.thindie.designsystem.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <T> ViewModel.act(action: suspend () -> T) {
  viewModelScope.launch {
        action()
    }
}