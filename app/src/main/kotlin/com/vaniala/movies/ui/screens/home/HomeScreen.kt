package com.vaniala.movies.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.vaniala.movies.R
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.ui.components.MovieItem
import com.vaniala.movies.ui.theme.titleSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(uiState: HomeUiState) {
    val moviesPaging = uiState.moviesPagingData?.collectAsLazyPagingItems()
    moviesPaging?.let {
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

        Box(modifier = Modifier.fillMaxSize()) {
            if (moviesPaging.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            } else {
                MovieList(moviesPaging)
            }
        }
    }
}

@Composable
private fun MovieList(moviesPaging: LazyPagingItems<Movie>) {
    Column {
        Text(
            text = stringResource(R.string.movies_popular),
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
                    MovieItem(it)
                }
            }
            item {
                if (moviesPaging.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
