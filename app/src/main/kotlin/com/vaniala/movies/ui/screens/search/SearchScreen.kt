package com.vaniala.movies.ui.screens.search

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.vaniala.movies.R
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.ui.components.GridMovies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(uiState: SearchUiState, onQueryChange: (String) -> Unit = {}, onMovieClick: (Movie) -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SearchBar(
            query = uiState.query,
            onQueryChange = onQueryChange,
            onSearch = {},
            active = false,
            onActiveChange = {},
            windowInsets = WindowInsets(top = 0),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text(text = stringResource(R.string.search_movies)) },
        ) {}

        Column(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = uiState.searchResults != null,
                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }),
            ) {
                uiState.searchResults?.collectAsLazyPagingItems()?.let { moviesPaging ->
                    val context = LocalContext.current
                    LaunchedEffect(key1 = moviesPaging.loadState) {
                        if (moviesPaging.loadState.refresh is LoadState.Error) {
                            Toast.makeText(
                                context,
                                "Error: " + (moviesPaging.loadState.refresh as LoadState.Error).error.message,
                                Toast.LENGTH_LONG,
                            ).show()
                        }
                    }

                    GridMovies(
                        hasRemove = false,
                        moviesPaging = moviesPaging,
                        onMovieClick = onMovieClick,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(SearchUiState())
}
