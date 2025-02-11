package com.vaniala.movies.navigation

import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch
import timber.log.Timber

fun NavGraphBuilder.movieDetailsScreen(onPopBackStack: () -> Unit, onNavigateToMovieDetails: (Int) -> Unit) {
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
            val context = LocalContext.current
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()
            val scope = rememberCoroutineScope()
            LaunchedEffect(Unit) {
                viewModel.getMovieDetails(id)
            }
            LaunchedEffect(uiState.messageError) {
                uiState.messageError?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            MovieDetailsScreen(
                uiState,
                toggleFavorite = {
                    scope.launch {
                        viewModel.toggleFavorite(id, it)
                    }
                },
                toggleWatchlist = {
                    scope.launch {
                        viewModel.toggleWatchlist(id, it)
                    }
                },
                onMovieClick = onNavigateToMovieDetails,
            )
        } ?: LaunchedEffect(Unit) {
            onPopBackStack()
        }
    }
}

fun NavController.navigateToMovieDetails(id: Int, navOptions: NavOptions? = null) {
    navigate("${MovieDetailsScreenDestination.route}/$id", navOptions)
}
