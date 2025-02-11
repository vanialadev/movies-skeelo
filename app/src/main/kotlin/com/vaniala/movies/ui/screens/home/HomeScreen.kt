package com.vaniala.movies.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vaniala.movies.R
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.sampledata.sampleMoviePageData
import com.vaniala.movies.ui.components.MovieItem
import com.vaniala.movies.ui.components.MovieListSectionSkeleton
import com.vaniala.movies.ui.theme.titleSection

@Composable
fun HomeScreen(uiState: HomeUiState, onMovieClick: (Movie) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        uiState.popularMoviesPagingData?.collectAsLazyPagingItems()?.let { moviesPaging ->
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

            MovieList(
                title = stringResource(R.string.movies_popular),
                moviesPaging = moviesPaging,
                onMovieClick = onMovieClick,
            )
        }

        uiState.topRatedMoviesPagingData?.collectAsLazyPagingItems()?.let { moviesPaging ->
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

            MovieList(
                title = stringResource(R.string.movies_top_rated),
                moviesPaging = moviesPaging,
                onMovieClick = onMovieClick,
            )
        }
    }
}

@Composable
private fun MovieList(title: String, moviesPaging: LazyPagingItems<Movie>, onMovieClick: (Movie) -> Unit = {}) {
    if (moviesPaging.loadState.refresh !is LoadState.Loading) {
        Column {
            Text(
                text = title,
                style = titleSection,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            LazyRow(
                modifier = Modifier
                    .height(250.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(moviesPaging.itemCount) { index ->
                    val movie = moviesPaging[index]
                    movie?.let {
                        MovieItem(it, onMovieClick)
                    }
                }
                item {
                    if (moviesPaging.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    } else {
        MovieListSectionSkeleton()
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListPreview() {
    val sampleListMoviePageData = sampleMoviePageData.collectAsLazyPagingItems()
    MovieList(stringResource(R.string.movies_popular), sampleListMoviePageData)
}

@Preview(showBackground = true)
@Composable
fun MovieListLoadingPreview() {
    val sampleListMoviePageData = sampleMoviePageData.collectAsLazyPagingItems()
    MovieList(stringResource(R.string.movies_popular), sampleListMoviePageData)
}
