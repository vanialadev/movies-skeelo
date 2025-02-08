package com.vaniala.movies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vaniala.movies.ui.screens.home.HomeScreen

internal const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen() {
    composable(HOME_ROUTE) {
        HomeScreen()
    }
}

fun NavController.navigateToHome() {
    navigate(HOME_ROUTE)
}
