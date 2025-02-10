package com.vaniala.movies.ui.screens.moviedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vaniala.movies.R
import com.vaniala.movies.domain.model.MovieAllDetails
import com.vaniala.movies.domain.model.MovieDetails
import com.vaniala.movies.domain.model.MovieStatus
import com.vaniala.movies.ui.components.AddStatusButton
import com.vaniala.movies.ui.components.ToggleButton
import com.vaniala.movies.ui.utils.Constants.IMAGE_URL_ORIGINAL

@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsUiState,
    toggleFavorite: (Boolean) -> Unit = {},
    toggleWatchlist: (Boolean) -> Unit = {},
) {
    val movieDetails = uiState.movieAllDetails?.movieDetails
    val movieStatus = uiState.movieAllDetails?.movieStatus
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                movieDetails?.title ?: String(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data("$IMAGE_URL_ORIGINAL${movieDetails?.backdropPath}")
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .build(),
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(2.dp)),
                placeholder = ColorPainter(Color.Gray),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                MovieStatusDetails(
                    movieStatus = movieStatus,
                    toggleFavorite = toggleFavorite,
                    toggleWatchlist = toggleWatchlist,
                )
            }
        }
    }
}

@Composable
fun MovieStatusDetails(
    movieStatus: MovieStatus?,
    toggleFavorite: (Boolean) -> Unit = {},
    toggleWatchlist: (Boolean) -> Unit = {},
) {
    Row {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val toggleButtonFavorite =
                if (movieStatus?.favorite == true) {
                    ToggleButton(
                        icon = Icons.Filled.Favorite,
                        text = stringResource(R.string.remove_favorites),
                        action = {
                            toggleFavorite(false)
                        },
                    )
                } else {
                    ToggleButton(
                        icon = Icons.Outlined.FavoriteBorder,
                        text = stringResource(R.string.favorite),
                        action = {
                            toggleFavorite(true)
                        },
                    )
                }
            val toggleButtonWatchlist =
                if (movieStatus?.watchlist == true) {
                    ToggleButton(
                        icon = Icons.Filled.Clear,
                        text = stringResource(R.string.remove_watchlist),
                        action = {
                            toggleWatchlist(false)
                        },
                    )
                } else {
                    ToggleButton(
                        icon = Icons.Outlined.Add,
                        text = stringResource(R.string.watchlist),
                        action = {
                            toggleWatchlist(true)
                        },
                    )
                }
            AddStatusButton(toggleButtonFavorite, onClick = {
                toggleButtonFavorite.action()
            })
            AddStatusButton(toggleButtonWatchlist, onClick = {
                toggleButtonWatchlist.action()
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        MovieDetailsUiState(
            movieAllDetails = MovieAllDetails(
                movieDetails = MovieDetails(
                    title = "Sonic 3",
                ),
            ),
        ),
    )
}
