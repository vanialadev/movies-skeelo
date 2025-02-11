package com.vaniala.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vaniala.movies.R
import com.vaniala.movies.navigation.MovieNavHost
import com.vaniala.movies.navigation.ScreensDestinations.HomeScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.MovieDetailsScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.navigation.navigateToBottomAppBarItem
import com.vaniala.movies.preferences.ThemePreferences
import com.vaniala.movies.ui.components.BottomAppBarItem
import com.vaniala.movies.ui.components.MovieBottomAppBar
import com.vaniala.movies.ui.components.ThemeIcon
import com.vaniala.movies.ui.components.bottomAppBarItems
import com.vaniala.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

const val DURATION = 1500L

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var keepSplashScreen = true
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        setContent {
            val context = LocalContext.current
            val themePreferences = remember { ThemePreferences(context) }
            val isDarkTheme by themePreferences.isDarkTheme.collectAsState(initial = false)
            val scope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                delay(DURATION)
                keepSplashScreen = false
            }

            MoviesTheme(darkTheme = isDarkTheme) {
                MoviesApp(
                    isDarkTheme = isDarkTheme,
                    onThemeUpdate = { newTheme ->
                        scope.launch {
                            themePreferences.setDarkTheme(newTheme)
                        }
                    },
                )
            }
        }
    }
}

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesApp(
    isDarkTheme: Boolean,
    onThemeUpdate: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController(),
) {
    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, _, _ ->
            val routes = navController.backQueue.map { it.destination.route }
            Timber.i("routes: $routes")
        }
    }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val bottomAppBarItemSelected = when (currentRoute) {
        ProfileScreenDestination.route -> BottomAppBarItem.Profile
        else -> BottomAppBarItem.Home
    }

    val isShowBottomAppBar = currentRoute in listOf(
        HomeScreenDestination.route,
        ProfileScreenDestination.route,
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            when (currentRoute) {
                HomeScreenDestination.route -> {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.app_name)) },
                        actions = {
                            ThemeIcon(
                                isDarkTheme = isDarkTheme,
                                onThemeUpdated = { onThemeUpdate(!isDarkTheme) },
                            )
                        },
                    )
                }
                ProfileScreenDestination.route -> {
                    TopAppBar(
                        title = { Text(text = stringResource(R.string.profile_title)) },
                        actions = {
                            ThemeIcon(
                                isDarkTheme = isDarkTheme,
                                onThemeUpdated = { onThemeUpdate(!isDarkTheme) },
                            )
                        },
                    )
                }
                else -> {
                    if (currentRoute?.startsWith(MovieDetailsScreenDestination.route) == true) {
                        TopAppBar(
                            title = { },
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null,
                                    Modifier
                                        .padding(16.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            navController.navigateUp()
                                        },
                                )
                            },
                        )
                    }
                }
            }
        },
        bottomBar = {
            if (isShowBottomAppBar) {
                MovieBottomAppBar(
                    selectedItem = bottomAppBarItemSelected,
                    items = bottomAppBarItems,
                    onItemClick = { item ->
                        navController.navigateToBottomAppBarItem(item)
                    },
                )
            }
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,
        ) {
            MovieNavHost(navController)
        }
    }
}
