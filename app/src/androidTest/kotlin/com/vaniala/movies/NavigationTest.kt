package com.vaniala.movies

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.vaniala.movies.di.TestAppModule
import com.vaniala.movies.navigation.ScreensDestinations
import com.vaniala.movies.navigation.ScreensDestinations.ProfileScreenDestination
import com.vaniala.movies.navigation.ScreensDestinations.SettingsScreenDestination
import com.vaniala.movies.ui.MoviesApp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("SwallowedException")
@HiltAndroidTest
@UninstallModules(TestAppModule::class)
class NavigationTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MoviesApp(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule.onRoot().printToLog("akuaku")
        composeTestRule.waitUntil(timeoutMillis = 2000) {
            try {
                composeTestRule
                    .onNodeWithTag("HomeScreen")
                    .assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }
    }

    @Test
    fun appNavHost_verifyIfProfileIsDisplay() {
        composeTestRule.onRoot().printToLog("akuaku")
        composeTestRule.waitUntil(timeoutMillis = 2000) {
            try {
                composeTestRule
                    .onNodeWithTag("HomeScreen")
                    .assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        composeTestRule
            .onNodeWithText("Perfil")
            .performClick()

        composeTestRule
            .onNodeWithTag("ProfileScreen")
            .assertExists()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, ProfileScreenDestination.route)
    }

    @Test
    fun appNavHost_verifyIfSettingsIsDisplayFromHome() {
        composeTestRule.onRoot().printToLog("akuaku")
        composeTestRule.waitUntil(timeoutMillis = 2000) {
            try {
                composeTestRule
                    .onNodeWithTag("HomeScreen")
                    .assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        composeTestRule
            .onNodeWithTag("settings")
            .performClick()

        composeTestRule
            .onNodeWithText("Tema")
            .assertExists()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, SettingsScreenDestination.route)
    }

    @Test
    fun appNavHost_verifyIfSettingsIsDisplayFromProfile() {
        composeTestRule.onRoot().printToLog("akuaku")
        composeTestRule.waitUntil(timeoutMillis = 2000) {
            try {
                composeTestRule
                    .onNodeWithTag("HomeScreen")
                    .assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        composeTestRule
            .onNodeWithText("Perfil")
            .performClick()

        composeTestRule
            .onNodeWithTag("ProfileScreen")
            .assertExists()

        composeTestRule
            .onNodeWithTag("settings")
            .performClick()

        composeTestRule
            .onNodeWithText("Tema")
            .assertExists()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, SettingsScreenDestination.route)
    }

    @Test
    fun appNavHost_verifyNavigationToMovieDetails() {
        val movieId = 123

        composeTestRule.runOnUiThread {
            navController.navigate("${ScreensDestinations.MovieDetailsScreenDestination.route}/$movieId")
        }

        composeTestRule
            .onNodeWithTag("MovieDetailsScreen")
            .assertExists()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, ScreensDestinations.MovieDetailsScreenDestination.routeWithArgs)
    }

    @Test
    fun appNavHost_verifyAllNavigation() {
        composeTestRule.onRoot().printToLog("akuaku")
        composeTestRule.waitUntil(timeoutMillis = 2000) {
            try {
                composeTestRule
                    .onNodeWithTag("HomeScreen")
                    .assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        composeTestRule
            .onNodeWithTag("settings")
            .performClick()

        composeTestRule
            .onNodeWithText("Tema")
            .assertExists()

        composeTestRule
            .onNodeWithTag("onBackNavigation")
            .performClick()

        composeTestRule
            .onNodeWithText("Perfil")
            .performClick()

        composeTestRule
            .onNodeWithTag("ProfileScreen")
            .assertExists()

        composeTestRule
            .onNodeWithText("Ver Depois")
            .performClick()

        composeTestRule.waitUntil(timeoutMillis = 2000) {
            try {
                composeTestRule
                    .onNodeWithTag("gridMovies")
                    .assertExists()
                true
            } catch (e: AssertionError) {
                false
            }
        }

        composeTestRule
            .onNodeWithTag("movieItem_0")
            .performClick()

        composeTestRule
            .onNodeWithTag("MovieDetailsScreen")
            .assertExists()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, ScreensDestinations.MovieDetailsScreenDestination.routeWithArgs)
    }
}
