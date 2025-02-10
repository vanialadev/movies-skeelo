package com.vaniala.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vaniala.movies.R
import com.vaniala.movies.navigation.MovieNavHost
import com.vaniala.movies.navigation.ScreensDestinations.HomeScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.MovieDetailsScreenDestination
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

    val isShowBackNavigation = when (currentRoute) {
        HomeScreenDestination.route, ProfileScreenDestination.route -> false
        else -> false
    }
    val isShowBottomAppBar = when (currentRoute) {
        HomeScreenDestination.route, ProfileScreenDestination.route -> true
        else -> false
    }
    MovieApp(
        bottomAppBarItemSelected = selectedBottomAppBarItem,
        isShowBackNavigation = isShowBackNavigation,
        isShowBottomAppBar = isShowBottomAppBar,
        bottomAppBarItems = bottomAppBarItems,
        onBackNavigationClick = {
            navController.popBackStack()
        },
        onBottomAppBarItemSelectedChange = { item ->
            navController.navigateToBottomAppBarItem(item)
        },
        isShowTopBar = isShowBottomAppBar,
        topAppBarTitle = {
            when (currentRoute) {
                ProfileScreenDestination.route -> Text(stringResource(R.string.profile_title))
                MovieDetailsScreenDestination.route, MovieDetailsScreenDestination.routeWithArgs -> Text(
                    stringResource(R.string.details),
                )
            }
        },
    ) {
        MovieNavHost(navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(
    bottomAppBarItems: List<BottomAppBarItem>,
    topAppBarTitle: @Composable () -> Unit = {},
    onBackNavigationClick: () -> Unit = {},
    isShowBackNavigation: Boolean = false,
    isShowBottomAppBar: Boolean = false,
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    isShowTopBar: Boolean = false,
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            if (isShowTopBar) {
                TopAppBar(
                    title = {
                        topAppBarTitle()
                    },
                    navigationIcon = {
                        if (isShowBackNavigation) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                Modifier
                                    .padding(16.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        onBackNavigationClick()
                                    },
                            )
                        }
                    },
                )
            }
        },
        bottomBar = {
            if (isShowBottomAppBar) {
                MovieBottomAppBar(
                    selectedItem = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemClick = onBottomAppBarItemSelectedChange,
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            content()
        }
    }
}
