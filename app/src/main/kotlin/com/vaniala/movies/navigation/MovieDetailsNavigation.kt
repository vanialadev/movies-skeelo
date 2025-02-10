package com.vaniala.movies.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vaniala.movies.navigation.ScreensDestinations.MovieDetailsScreenDestination
import com.vaniala.movies.ui.screens.moviedetails.MovieDetailsScreen
import com.vaniala.movies.ui.screens.moviedetails.MovieDetailsViewModel
import timber.log.Timber

fun NavGraphBuilder.movieDetailsScreen(onPopBackStack: () -> Unit) {
    composable(
        route = MovieDetailsScreenDestination.routeWithArgs,
        arguments = listOf(
            navArgument(MovieDetailsScreenDestination.argument) {
                defaultValue = 0
                type = NavType.IntType
            },
        ),
    ) { backStackEntry ->
        backStackEntry.arguments?.getInt(MovieDetailsScreenDestination.argument)?.let { id ->
            Timber.d("id: $id")
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            LaunchedEffect(Unit) {
                viewModel.getMovieDetails(id)
            }
            MovieDetailsScreen(uiState)
        } ?: LaunchedEffect(Unit) {
            onPopBackStack()
        }
    }
}

fun NavController.navigateToMovieDetails(id: Long, navOptions: NavOptions? = null) {
    navigate("${MovieDetailsScreenDestination.route}/$id", navOptions)
}
