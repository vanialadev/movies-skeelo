package com.vaniala.movies.ui.components

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
import com.vaniala.movies.data.mappers.Mappers.toModel
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.domain.model.Movie

@Composable
fun MovieItem(movie: Movie) {
    Column(
        Modifier
            .height(220.dp)
            .width(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = movie.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        AsyncImagePoster(movie.images.posters)
    }
}

@Preview
@Composable
private fun MoviePreview() {
    val movie = MovieResponse(title = "Moana 2").toModel()
    MovieItem(movie = movie)
}
