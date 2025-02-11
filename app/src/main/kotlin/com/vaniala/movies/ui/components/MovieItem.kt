package com.vaniala.movies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.extensions.toOneDecimal

@Composable
fun MovieItem(movie: Movie, onMovieClick: (Movie) -> Unit = {}) {
    Column(
        Modifier
            .height(220.dp)
            .width(120.dp)
            .clickable { onMovieClick(movie) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = movie.title ?: String(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        AsyncImagePoster(movie.images?.posters, movie.voteAverage?.toOneDecimal())
    }
}

@Preview
@Composable
private fun MoviePreview() {
    val movie = Movie(title = "Moana 2")
    MovieItem(movie = movie)
}
