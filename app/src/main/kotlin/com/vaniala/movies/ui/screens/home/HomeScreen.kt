package com.vaniala.movies.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

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
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
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
                    item {
                        if (moviesPaging.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}
