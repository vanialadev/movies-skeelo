package com.vaniala.movies.ui.screens.moviedetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vaniala.movies.ui.utils.Constants.IMAGE_URL_ORIGINAL

@Composable
fun MovieDetailsScreen(uiState: MovieDetailsUiState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            uiState.movieDetails?.title?.let {
                Text(it)
            }

            AsyncImage(
                model = IMAGE_URL_ORIGINAL + uiState.movieDetails?.backdropPath,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(2.dp)),
                placeholder = ColorPainter(Color.Gray),
                contentScale = ContentScale.Crop,
            )
        }
    }
}
