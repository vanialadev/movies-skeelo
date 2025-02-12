package com.vaniala.movies.ui.screens.moviedetails

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vaniala.movies.R
import com.vaniala.movies.domain.model.Genres
import com.vaniala.movies.domain.model.MovieAllDetails
import com.vaniala.movies.domain.model.MovieDetails
import com.vaniala.movies.ui.components.AnimatedIcon
import com.vaniala.movies.ui.components.AsyncImagePoster
import com.vaniala.movies.ui.components.ImageBottomSheet
import com.vaniala.movies.ui.components.MovieDetailsSkeleton
import com.vaniala.movies.ui.utils.Constants.IMAGE_URL_ORIGINAL
import com.vaniala.movies.utils.downloadImage
import com.vaniala.movies.utils.shareImage
import kotlin.random.Random

// todo:v refactor cirar componentes
@Suppress("MagicNumber", "LongMethod")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsUiState,
    toggleFavorite: (Boolean) -> Unit = {},
    toggleWatchlist: (Boolean) -> Unit = {},
    onMovieClick: (Int) -> Unit = {},
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("MovieDetailsScreen"),
    ) {
        if (uiState.isLoading) {
            MovieDetailsSkeleton()
        } else {
            val sheetState = rememberModalBottomSheetState()
            var showBottomSheet by remember { mutableStateOf(false) }
            var selectedImageUrl by remember { mutableStateOf("") }

            if (showBottomSheet) {
                ImageBottomSheet(
                    sheetState = sheetState,
                    onDismiss = { showBottomSheet = false },
                    onShare = {
                        context.shareImage(selectedImageUrl)
                    },
                    onDownload = {
                        val fileName = "movie_${System.currentTimeMillis()}.jpg"
                        context.downloadImage(selectedImageUrl, fileName)
                    },

                )
            }

            val movieDetails = uiState.movieAllDetails?.movieDetails
            val movieStatus = uiState.movieAllDetails?.movieStatus

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                AsyncImage(
                    ImageRequest.Builder(LocalContext.current)
                        .data("$IMAGE_URL_ORIGINAL${movieDetails?.backdropPath}")
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.error)
                        .build(),
                    contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .combinedClickable(
                            onClick = {},
                            onLongClick = {
                                selectedImageUrl = "$IMAGE_URL_ORIGINAL${movieDetails?.backdropPath}"
                                showBottomSheet = true
                            },
                        ),
                    placeholder = ColorPainter(Color.Gray),
                    contentScale = ContentScale.FillWidth,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        ImageRequest.Builder(LocalContext.current)
                            .data("$IMAGE_URL_ORIGINAL${movieDetails?.posterPath}")
                            .placeholder(R.drawable.placeholder)
                            .error(R.drawable.error)
                            .build(),
                        contentDescription = null,
                        Modifier
                            .width(100.dp)
                            .height(150.dp),
                        placeholder = ColorPainter(Color.Gray),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = movieDetails?.title ?: "", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(text = movieDetails?.tagline ?: "", fontSize = 14.sp)
                        Text(
                            text = stringResource(
                                R.string.votes,
                            ) + " ${movieDetails?.voteAverage} (${movieDetails?.voteCount} votes)",
                            fontSize = 12.sp,
                        )
                    }
                }

                val toggleButtonFavorite =
                    if (movieStatus?.favorite == true) {
                        ToggleButton(
                            icon = Icons.Filled.Favorite,
                            action = {
                                toggleFavorite(false)
                            },
                        )
                    } else {
                        ToggleButton(
                            icon = Icons.Outlined.FavoriteBorder,
                            action = {
                                toggleFavorite(true)
                            },
                        )
                    }
                val toggleButtonWatchlist =
                    if (movieStatus?.watchlist == true) {
                        ToggleButton(
                            icon = Icons.Filled.Clear,
                            action = {
                                toggleWatchlist(false)
                            },
                        )
                    } else {
                        ToggleButton(
                            icon = Icons.Outlined.Add,
                            action = {
                                toggleWatchlist(true)
                            },
                        )
                    }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        modifier = Modifier.clickable {
                            toggleButtonFavorite.action()
                        },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AnimatedIcon(
                            isActive = movieStatus?.favorite == true,
                            activeIcon = Icons.Filled.Favorite,
                            inactiveIcon = Icons.Outlined.FavoriteBorder,
                        )
                        Text(
                            text = stringResource(R.string.favorites),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                    Row(
                        modifier = Modifier.clickable {
                            toggleButtonWatchlist.action()
                        },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AnimatedIcon(
                            isActive = movieStatus?.watchlist == true,
                            activeIcon = Icons.Filled.Clear,
                            inactiveIcon = Icons.Outlined.Add,
                        )
                        Text(
                            text = stringResource(R.string.watchlist),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = stringResource(R.string.genres), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    movieDetails?.genres?.let { genres ->
                        Text(text = genres.joinToString { it.name ?: "" })
                    }
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = stringResource(R.string.overview), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = movieDetails?.overview ?: "")
                }

                if (!uiState.recommendations.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.recommendations),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp),
                        ) {
                            items(uiState.recommendations) { movie ->
                                AsyncImagePoster(
                                    url =
                                    "$IMAGE_URL_ORIGINAL${movie.posterPath}",
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(180.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { movie.id?.let { onMovieClick(it) } },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

class ToggleButton(val icon: ImageVector, val action: () -> Unit)

@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenPreview() {
    Surface {
        MovieDetailsScreen(
            MovieDetailsUiState(
                isLoading = false,
                movieAllDetails = MovieAllDetails(
                    movieDetails = MovieDetails(
                        title = "Sonic 3",
                        genres = listOf(Genres(id = 1, name = "Drama"), Genres(id = 2, name = "comedia")),
                        overview = LoremIpsum(1).values.joinToString(" "),
                        voteAverage = Random.nextDouble(0.0, 10.0),
                        voteCount = Random.nextInt(100, 10000),
                    ),
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieDetailsScreenLoadingPreview() {
    Surface {
        MovieDetailsScreen(
            MovieDetailsUiState(
                movieAllDetails = MovieAllDetails(
                    movieDetails = MovieDetails(
                        title = "Sonic 3",
                        genres = listOf(Genres(id = 1, name = "Drama"), Genres(id = 2, name = "comedia")),
                        overview = LoremIpsum(1).values.joinToString(" "),
                        voteAverage = Random.nextDouble(0.0, 10.0),
                        voteCount = Random.nextInt(100, 10000),
                    ),
                ),
            ),
        )
    }
}
