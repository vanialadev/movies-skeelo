package com.vaniala.movies.navigation

private const val HOME_ROUTE = "home"
private const val PROFILE_ROUTE = "profile"

sealed class ScreensDestinations(val route: String) {
    data object HomeScreenDestination : ScreensDestinations(HOME_ROUTE)
    data object ProfileScreenDestination : ScreensDestinations(PROFILE_ROUTE)
}
