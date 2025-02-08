package com.vaniala.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vaniala.movies.HomeViewModel
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.navigation.HOME_ROUTE
import com.vaniala.movies.navigation.homeScreen
import com.vaniala.movies.navigation.navigateToHome
import com.vaniala.movies.navigation.navigateToProfile
import com.vaniala.movies.navigation.profileScreen
import com.vaniala.movies.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viwModel: HomeViewModel by viewModels()

            val moviesPaging = viwModel.getMovies().collectAsLazyPagingItems()
            MoviesTheme {
                MovieApp()
            }
        }
    }
}

@Composable
fun MovieApp(navController: NavHostController = rememberNavController()) {
    val backStackEntryAsState by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryAsState?.destination

    val currentRoute = currentDestination?.route

    val selectedBottomAppBarItem = when (currentRoute) {
        HOME_ROUTE -> BottomAppBarItem.Home
        else -> BottomAppBarItem.Profile
    }

    MovieApp(
        selectedBottomAppBarItem,
        onBottomAppBarItemSelectedChange = { item ->
            navController.navigateToBottomAppBarItem(item)
        },
    ) {
        NavHost(navController = navController, startDestination = HOME_ROUTE) {
            homeScreen()
            profileScreen()
        }
    }
}

fun NavController.navigateToBottomAppBarItem(item: BottomAppBarItem) {
    if (item == BottomAppBarItem.Home) {
        navigateToHome()
    } else {
        navigateToProfile()
    }
}

@Composable
fun MovieApp(
    selectedBottomAppBarItem: BottomAppBarItem,
    onBottomAppBarItemSelectedChange: (BottomAppBarItem) -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        bottomBar = {
            MovieBottomAppBar(
                selectedItem = selectedBottomAppBarItem,
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

@Composable
fun Teste(moviesPaging: LazyPagingItems<Movie>) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(moviesPaging.itemCount) {
                Box(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = (moviesPaging[it]?.title ?: String()),
                        color = Color.Black,
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
