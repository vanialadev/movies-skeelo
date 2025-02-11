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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vaniala.movies.R
import com.vaniala.movies.navigation.MovieNavHost
import com.vaniala.movies.navigation.ScreensDestinations.HomeScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.navigation.navigateToBottomAppBarItem
import com.vaniala.movies.navigation.navigateToSettings
import com.vaniala.movies.preferences.ThemePreferences
import com.vaniala.movies.ui.components.BottomAppBarItem
import com.vaniala.movies.ui.components.MovieBottomAppBar
import com.vaniala.movies.ui.components.bottomAppBarItems
import com.vaniala.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {
    @Inject
    lateinit var themePreferences: ThemePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var keepSplashScreen = true
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

        setContent {
            val isDarkTheme by themePreferences.isDarkTheme.collectAsState(initial = false)
            val navController = rememberNavController()
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStack?.destination?.route

            LaunchedEffect(currentRoute) {
                if (currentRoute == HomeScreenDestination.route) {
                    keepSplashScreen = false
                }
            }

            MoviesTheme(darkTheme = isDarkTheme) {
                MoviesApp(navController)
            }
        }
    }
}

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesApp(navController: NavHostController) {
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

    MoviesApp(
        bottomAppBarItemSelected = bottomAppBarItemSelected,
        isShowBottomAppBar = isShowBottomAppBar,
        onBottomAppBarItemSelectedChange = {
            navController.navigateToBottomAppBarItem(it)
        },
        goToSettings = {
            navController.navigateToSettings()
        },
        onBackNavigationClick = {
            navController.navigateUp()
        },
        topAppBarTitle = {
            when (currentRoute) {
                HomeScreenDestination.route -> {
                    Text(text = stringResource(R.string.app_name))
                }

                ProfileScreenDestination.route -> {
                    Text(text = stringResource(R.string.profile_title))
                }
            }
        },
    ) {
        MovieNavHost(navController)
    }
}

@Suppress("LongMethod")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesApp(
    isShowBottomAppBar: Boolean = false,
    bottomAppBarItemSelected: BottomAppBarItem = bottomAppBarItems.first(),
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    goToSettings: () -> Unit = {},
    onBackNavigationClick: () -> Unit = {},
    topAppBarTitle: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopAppBar(
                title = {
                    topAppBarTitle()
                },
                actions = {
                    if (isShowBottomAppBar) {
                        IconButton(onClick = goToSettings, Modifier.testTag("settings")) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = stringResource(R.string.settings),
                            )
                        }
                    }
                },
                navigationIcon = {
                    if (!isShowBottomAppBar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            Modifier
                                .padding(16.dp)
                                .clip(CircleShape)
                                .testTag("onBackNavigation")
                                .clickable {
                                    onBackNavigationClick()
                                },
                        )
                    }
                },
            )
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,
        ) {
            content()
        }
    }
}
