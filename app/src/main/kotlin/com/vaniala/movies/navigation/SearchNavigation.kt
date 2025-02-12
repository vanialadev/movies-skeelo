package com.vaniala.movies.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.navigation.ScreensDestinations.SearchScreenDestination
import com.vaniala.movies.ui.screens.search.SearchScreen
import com.vaniala.movies.ui.screens.search.SearchViewModel

fun NavGraphBuilder.searchScreen(onNavigateToMovieDetails: (Movie) -> Unit) {
    composable(SearchScreenDestination.route) {
        val viewModel = hiltViewModel<SearchViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        SearchScreen(
            uiState = uiState,
            onQueryChange = viewModel::onQueryChange,
            onMovieClick = onNavigateToMovieDetails,
        )
    }
}

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(SearchScreenDestination.route, navOptions)
}
