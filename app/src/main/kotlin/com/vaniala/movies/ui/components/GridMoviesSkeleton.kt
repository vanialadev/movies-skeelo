package com.vaniala.movies.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vaniala.movies.ui.utils.Constants.SIZE_SKELETON
import com.vaniala.movies.ui.utils.Constants.TRIPLE_COLUMN

@Composable
fun GridMoviesSkeleton() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(TRIPLE_COLUMN),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(SIZE_SKELETON) {
            MovieItemSkeleton()
        }
    }
}
