package com.vaniala.movies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.vaniala.movies.navigation.ScreensDestinations.HomeScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.ui.components.BottomAppBarItem

@Composable
fun MovieNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeScreenDestination.route) {
        homeScreen(
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

fun NavController.navigateToBottomAppBarItem(item: BottomAppBarItem) {
    if (item == BottomAppBarItem.Home) {
        navigateToHome(
            navOptions {
                launchSingleTop = true
                popUpTo(HomeScreenDestination.route)
            },
        )
    } else {
        navigateToProfile(
            navOptions {
                launchSingleTop = true
                popUpTo(ProfileScreenDestination.route)
            },
        )
    }
}
