package com.vaniala.movies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.vaniala.movies.ui.BottomAppBarItem

@Composable
fun MovieNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        homeScreen()
        profileScreen()
    }
}

fun NavController.navigateToBottomAppBarItem(item: BottomAppBarItem) {
    if (item == BottomAppBarItem.Home) {
        navigateToHome(
            navOptions {
                launchSingleTop = true
                popUpTo(HOME_ROUTE)
            },
        )
    } else {
        navigateToProfile(
            navOptions {
                launchSingleTop = true
                popUpTo(PROFILE_ROUTE)
            },
        )
    }
}
