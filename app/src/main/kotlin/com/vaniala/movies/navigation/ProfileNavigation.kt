package com.vaniala.movies.navigation

import android.widget.Toast
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.ui.screens.profile.ProfileScreen
import com.vaniala.movies.ui.screens.profile.ProfileViewModel

fun NavGraphBuilder.profileScreen() {
    composable(ProfileScreenDestination.route) {
        val context = LocalContext.current
        val viewModel = hiltViewModel<ProfileViewModel>()
        val state by viewModel.uiState.collectAsState()
        ProfileScreen(
            state,
            onRemoveMovieFromWatchlist = {
                Toast.makeText(context, "onRemoveMovieFromWatchlist", Toast.LENGTH_SHORT).show()
            },
            onRemoveMovieFromFavorite = {
                Toast.makeText(context, "onRemoveMovieFromFavorite", Toast.LENGTH_SHORT).show()
            },
        )
    }
}

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(ProfileScreenDestination.route, navOptions)
}
