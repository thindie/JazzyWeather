package com.example.jazzyweather.navigation

import androidx.navigation.NavHostController

class NavigationState(val navHostController: NavHostController) {

    val startDestination = destinations.find {
        navHostController.currentDestination?.route == it.destination()
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