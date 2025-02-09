package com.vaniala.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vaniala.movies.navigation.MovieNavHost
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.navigation.navigateToBottomAppBarItem
import com.vaniala.movies.ui.components.BottomAppBarItem
import com.vaniala.movies.ui.components.MovieBottomAppBar
import com.vaniala.movies.ui.components.bottomAppBarItems
import com.vaniala.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {
                MovieApp()
            }
        }
    }
}

@Composable
fun MovieApp(navController: NavHostController = rememberNavController()) {
    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, _, _ ->
            val routes =
                navController.backQueue.map {
                    it.destination.route
                }

            Timber.i("routes: $routes")
        }
    }

    val backStackEntryAsState by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryAsState?.destination
    val currentRoute = currentDestination?.route
    Timber.d("$currentRoute")

    val selectedBottomAppBarItem by remember(currentDestination) {
        val item =
            when (currentRoute) {
                ProfileScreenDestination.route -> BottomAppBarItem.Profile
                else -> BottomAppBarItem.Home
            }
        mutableStateOf(item)
    }

    MovieApp(
        bottomAppBarItemSelected = selectedBottomAppBarItem,
        bottomAppBarItems = bottomAppBarItems,
        onBottomAppBarItemSelectedChange = { item ->
            navController.navigateToBottomAppBarItem(item)
        },
    ) {
        MovieNavHost(navController = navController)
    }
}

@Composable
fun MovieApp(
    bottomAppBarItems: List<BottomAppBarItem>,
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            MovieBottomAppBar(
                selectedItem = bottomAppBarItemSelected,
                items = bottomAppBarItems,
                onItemClick = onBottomAppBarItemSelectedChange,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            content()
        }
    }
}
