package com.vaniala.movies.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.ProfileDetails
import com.vaniala.movies.extensions.getTabTitle
import com.vaniala.movies.ui.components.AsyncImageProfile
import com.vaniala.movies.ui.components.GridMovies
import com.vaniala.movies.ui.components.GridMoviesSkeleton
import com.vaniala.movies.ui.utils.Constants.IMAGE_URL_LARGE
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    state: ProfileUiState = ProfileUiState(),
    onRemoveFavorite: (Int, LazyPagingItems<Movie>) -> Unit = { _, _ -> },
    onRemoveWatchlist: (Int, LazyPagingItems<Movie>) -> Unit = { _, _ -> },
    onMovieClick: (Movie) -> Unit = {},
) {
    val favoritesPaging = state.favoritesPagingData?.collectAsLazyPagingItems()
    val watchlistPaging = state.watchlistPagingData?.collectAsLazyPagingItems()
    val context = LocalContext.current
    LaunchedEffect(key1 = favoritesPaging?.loadState) {
        if (favoritesPaging?.loadState?.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (favoritesPaging.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG,
            ).show()
        }
    }
    LaunchedEffect(key1 = watchlistPaging?.loadState) {
        if (watchlistPaging?.loadState?.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (watchlistPaging.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG,
            ).show()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
        Modifier
            .fillMaxSize(),
    ) {
        AsyncImageProfile(state.profileDetails?.avatar)
        state.nameUpdate?.let { name ->
            Text(
                text = name,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        val tabs = context.getTabTitle()
        val pagerState = rememberPagerState(pageCount = { tabs.size })
        Spacer(Modifier.height(16.dp))
        TabProfile(pagerState, tabs)
        HorizontalPageProfile(
            state = state,
            pagerState = pagerState,
            favoritesPaging = favoritesPaging,
            watchlistPaging = watchlistPaging,
            onRemoveFavorite = onRemoveFavorite,
            onRemoveWatchlist = onRemoveWatchlist,
            onMovieClick = onMovieClick,
        )
    }
}

@Composable
private fun TabProfile(pagerState: PagerState, tabs: List<Pair<String, ImageVector>>) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier = Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        tabs.forEachIndexed { index, pair: Pair<String, ImageVector> ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {
                    Icon(imageVector = pair.second, contentDescription = null)
                },
                text = {
                    Text(text = pair.first)
                },
            )
        }
    }
}

@Composable
fun HorizontalPageProfile(
    state: ProfileUiState,
    pagerState: PagerState,
    favoritesPaging: LazyPagingItems<Movie>?,
    watchlistPaging: LazyPagingItems<Movie>?,
    onRemoveFavorite: (Int, LazyPagingItems<Movie>) -> Unit = { _, _ -> },
    onRemoveWatchlist: (Int, LazyPagingItems<Movie>) -> Unit = { _, _ -> },
    onMovieClick: (Movie) -> Unit = {},
) {
    HorizontalPager(
        state = pagerState,
    ) { index: Int ->
        when (index) {
            0 -> favoritesPaging?.let {
                LoadStates(it) {
                    GridMovies(
                        state.removingFavorites,
                        favoritesPaging,
                        onRemoveFavorite,
                        onMovieClick,
                    )
                }
            }

            1 -> watchlistPaging?.let {
                LoadStates(it) {
                    GridMovies(
                        state.removingWatchlist,
                        watchlistPaging,
                        onRemoveWatchlist,
                        onMovieClick,
                    )
                }
            }
        }
    }
}

@Composable
fun LoadStates(paging: LazyPagingItems<Movie>, gridMovies: @Composable () -> Unit) {
    when (paging.loadState.refresh) {
        is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                GridMoviesSkeleton()
            }
        }

        is LoadState.NotLoading -> gridMovies()
        is LoadState.Error -> Text("Tratar Error")
    }
}

@Composable
fun MovieWithRemove(onRemove: (Int, LazyPagingItems<Movie>) -> Unit, movie: Movie?, moviesPaging: LazyPagingItems<Movie>) {
    Box {
        Box(
            Modifier
                .padding(8.dp)
                .background(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .align(Alignment.TopEnd)
                .clickable { movie?.id?.let { onRemove(it, moviesPaging) } }
                .padding(4.dp),
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = null,
                Modifier.align(
                    Alignment.Center,
                ),
                tint = Color.Black,
            )
        }
        AsyncImage(
            model = "$IMAGE_URL_LARGE${movie?.posterPath}",
            contentDescription = null,
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .zIndex(-1f),
            placeholder = ColorPainter(Color.Gray),
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(ProfileUiState(profileDetails = ProfileDetails(username = "vaniala")))
}
