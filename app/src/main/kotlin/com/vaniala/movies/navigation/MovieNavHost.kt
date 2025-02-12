package com.vaniala.movies.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.vaniala.movies.extensions.ScaleTransitionDirection
import com.vaniala.movies.extensions.scaleIntoContainer
import com.vaniala.movies.extensions.scaleOutOfContainer
import com.vaniala.movies.extensions.slideHorizontallyIntoContainer
import com.vaniala.movies.extensions.slideHorizontallyOutOfContainer
import com.vaniala.movies.extensions.slideVerticallyIntoContainer
import com.vaniala.movies.extensions.slideVerticallyOutOfContainer
import com.vaniala.movies.navigation.ScreensDestinations.HomeScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.MovieDetailsScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.SearchScreenDestination
import com.vaniala.movies.ui.components.BottomAppBarItem

@Composable
fun MovieNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        enterTransition = { enterTransition() },
        exitTransition = { exitTransition() },
        popEnterTransition = { popEnterTransition() },
        popExitTransition = { popExitTransition() },
    ) {
        homeScreen(
            onNavigateToMovieDetails = { movie ->
                movie.id?.let {
                    navController.navigateToMovieDetails(it)
                }
            },
        )
        searchScreen(
            onNavigateToMovieDetails = { movie ->
                movie.id?.let {
                    navController.navigateToMovieDetails(it)
                }
            },
        )
        profileScreen(
            onNavigateToMovieDetails = { movie ->
                movie.id?.let {
                    navController.navigateToMovieDetails(it)
                }
            },
        )
        movieDetailsScreen(
            onPopBackStack = {
                navController.navigateUp()
            },
            onNavigateToMovieDetails = { id ->
                navController.navigateToMovieDetails(id)
            },
        )
        settingsScreen()
    }
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition() = when (initialState.destination.route) {
    ProfileScreenDestination.route, SearchScreenDestination.route -> slideVerticallyOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
    )

    MovieDetailsScreenDestination.routeWithArgs -> scaleOutOfContainer(ScaleTransitionDirection.INWARDS)
    else -> slideHorizontallyOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition() = when (initialState.destination.route) {
    ProfileScreenDestination.route, SearchScreenDestination.route -> slideVerticallyIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Down,
    )

    MovieDetailsScreenDestination.routeWithArgs -> scaleIntoContainer(ScaleTransitionDirection.INWARDS)
    else -> slideHorizontallyIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition() = when (targetState.destination.route) {
    ProfileScreenDestination.route, SearchScreenDestination.route -> slideVerticallyOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
    )

    MovieDetailsScreenDestination.routeWithArgs -> scaleOutOfContainer(ScaleTransitionDirection.OUTWARDS)
    else -> slideHorizontallyOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
}

private fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition() = when (targetState.destination.route) {
    ProfileScreenDestination.route, SearchScreenDestination.route -> slideVerticallyIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Up,
    )

    MovieDetailsScreenDestination.routeWithArgs -> scaleIntoContainer(ScaleTransitionDirection.OUTWARDS)
    else -> slideHorizontallyIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
}

fun NavController.navigateToBottomAppBarItem(item: BottomAppBarItem) {
    when (item) {
        BottomAppBarItem.Home -> navigateToHome(
            navOptions {
                launchSingleTop = true
                popUpTo(HomeScreenDestination.route)
            },
        )
        BottomAppBarItem.Search -> navigateToSearch(
            navOptions {
                launchSingleTop = true
                popUpTo(SearchScreenDestination.route)
            },
        )
        BottomAppBarItem.Profile -> navigateToProfile(
            navOptions {
                launchSingleTop = true
                popUpTo(ProfileScreenDestination.route)
            },
        )
    }
}
