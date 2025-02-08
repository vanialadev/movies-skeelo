package com.vaniala.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.navigation.MovieNavHost
import com.vaniala.movies.navigation.PROFILE_ROUTE
import com.vaniala.movies.navigation.navigateToBottomAppBarItem
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
    val backStackEntryAsState by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryAsState?.destination
    val currentRoute = currentDestination?.route
    Timber.d("$currentRoute")
    val selectedBottomAppBarItem = when (currentRoute) {
        PROFILE_ROUTE -> BottomAppBarItem.Profile
        else -> BottomAppBarItem.Home
    }

    MovieApp(
        selectedBottomAppBarItem = selectedBottomAppBarItem,
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
    selectedBottomAppBarItem: BottomAppBarItem,
    bottomAppBarItems: List<BottomAppBarItem>,
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
