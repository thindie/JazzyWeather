package com.example.jazzyweather.navigation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Stable
class NavigationState(val navHostController: NavHostController, private val scope: CoroutineScope) {

    var shouldShowNavigationBar by mutableStateOf(true)
        private set

    val startDestination = destinations.find {
        navHostController.currentDestination?.route == it.destination()
    }

    private fun onNavigate() {
        scope.launch {
            shouldShowNavigationBar = false
            delay(1000)
            shouldShowNavigationBar = true
        }
    }

    fun concreteScreen() {
        navigate(concrete)
    }

    fun pickerScreen() {
        navigate(picker)
    }

    fun favoritesScreen() {
        navigate(all)
    }

    fun back() {
        navHostController.popBackStack()
    }

    fun navigate(route: String) {
        onNavigate()
        if (route == goBack) {
            back()
        } else {
            navHostController.navigate(route) {
                launchSingleTop = true
                this.restoreState = true
            }
        }

    }


}