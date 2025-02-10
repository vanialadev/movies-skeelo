package com.vaniala.movies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.ui.screens.profile.MovieWithRemove
import com.vaniala.movies.ui.utils.Constants.DOUBLE_COLUMN
import com.vaniala.movies.ui.utils.Constants.DOUBLE_COLUMN_MAX
import com.vaniala.movies.ui.utils.Constants.SINGLE_COLUMN
import com.vaniala.movies.ui.utils.Constants.SINGLE_COLUMN_MAX
import com.vaniala.movies.ui.utils.Constants.TRIPLE_COLUMN

@Composable
fun GridMovies(
    removingItems: Set<Int>,
    moviesPaging: LazyPagingItems<Movie>,
    onRemove: (Int, LazyPagingItems<Movie>) -> Unit,
    onMovieClick: (Movie) -> Unit = {},
) {
    val columns = remember(moviesPaging.itemCount) {
        getColumns(moviesPaging.itemCount)
    }
    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                moviesPaging.itemCount,
                key = { index -> moviesPaging.peek(index)?.id ?: index },
            ) { index ->
                val movie = moviesPaging[index]
                if (movie?.id !in removingItems) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable { movie?.let { onMovieClick(it) } },
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = movie?.title ?: String(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        MovieWithRemove(
                            movie = movie,
                            onRemove = onRemove,
                            moviesPaging = moviesPaging,
                        )
                    }
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

fun getColumns(size: Int): Int = when {
    size <= SINGLE_COLUMN_MAX -> SINGLE_COLUMN
    size <= DOUBLE_COLUMN_MAX -> DOUBLE_COLUMN
    else -> TRIPLE_COLUMN
}
