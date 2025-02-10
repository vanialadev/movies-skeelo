package com.vaniala.movies.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.ui.screens.profile.ProfileScreen
import com.vaniala.movies.ui.screens.profile.ProfileViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.profileScreen(onNavigateToMovieDetails: (Movie) -> Unit) {
    composable(ProfileScreenDestination.route) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val state by viewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()

        ProfileScreen(
            state,
            onRemoveFavorite = {
                scope.launch {
                    viewModel.removeFavorite(it)
                }
            },
            onRemoveWatchlist = {
                scope.launch {
                    viewModel.removeWatchlist(it)
                }
            },
            onMovieClick = onNavigateToMovieDetails,
        )
    }
}

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(ProfileScreenDestination.route, navOptions)
}
