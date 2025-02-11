package com.vaniala.movies.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vaniala.movies.navigation.ScreensDestinations.SettingsScreenDestination
import com.vaniala.movies.ui.screens.settings.SettingsScreen
import com.vaniala.movies.ui.screens.settings.SettingsViewModel

fun NavGraphBuilder.settingsScreen() {
    composable(SettingsScreenDestination.route) {
        val viewModel: SettingsViewModel = hiltViewModel()
        val isDarkTheme by viewModel.isDarkTheme.collectAsState()

        SettingsScreen(
            isDarkTheme = isDarkTheme,
            onThemeChanged = {
                viewModel.setDarkTheme(it)
            },
        )
    }
}

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    navigate(SettingsScreenDestination.route, navOptions)
}
