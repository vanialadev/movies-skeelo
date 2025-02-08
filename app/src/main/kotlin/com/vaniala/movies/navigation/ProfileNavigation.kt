package com.vaniala.movies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vaniala.movies.ui.screens.profile.ProfileScreen

internal const val PROFILE_ROUTE = "profile"

fun NavGraphBuilder.profileScreen() {
    composable(PROFILE_ROUTE) {
        ProfileScreen()
    }
}

fun NavController.navigateToProfile() {
    navigate(PROFILE_ROUTE)
}
