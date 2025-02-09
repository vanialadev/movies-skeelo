package com.vaniala.movies.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vaniala.movies.navigation.ScreensDestinations.HomeScreenDestination
import com.vaniala.movies.ui.screens.home.HomeScreen
import com.vaniala.movies.ui.screens.home.HomeViewModel

fun NavGraphBuilder.homeScreen() {
    composable(HomeScreenDestination.route) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        HomeScreen(uiState)
    }
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(HomeScreenDestination.route, navOptions)
}
