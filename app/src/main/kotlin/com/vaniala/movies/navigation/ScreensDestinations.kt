package com.vaniala.movies.navigation

private const val HOME_ROUTE = "home"
private const val PROFILE_ROUTE = "profile"
private const val SETTINGS_ROUTE = "settings"
private const val SEARCH_ROUTE = "search"
private const val MOVIE_DETAILS_ARGUMENT = "movie_details_id"
private const val MOVIE_DETAILS_ROUTE = "movie_details"
private const val MOVIE_DETAILS_WITH_ARGUMENT_ROUTE = "movie_details/{movie_details_id}"

sealed class ScreensDestinations(val route: String, val routeWithArgs: String = String(), val argument: String = String()) {
    data object HomeScreenDestination : ScreensDestinations(HOME_ROUTE)
    data object ProfileScreenDestination : ScreensDestinations(PROFILE_ROUTE)
    data object SettingsScreenDestination : ScreensDestinations(SETTINGS_ROUTE)
    data object SearchScreenDestination : ScreensDestinations(SEARCH_ROUTE)
    data object MovieDetailsScreenDestination : ScreensDestinations(
        MOVIE_DETAILS_ROUTE,
        MOVIE_DETAILS_WITH_ARGUMENT_ROUTE,
        MOVIE_DETAILS_ARGUMENT,
    )
}
