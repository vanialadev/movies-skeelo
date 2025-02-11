package com.vaniala.movies.domain

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.AddWatchListOrFavorite
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie

object TestData {

    val response = AddWatchListOrFavorite(
        success = true,
        statusMessage = "Success",
    )

    val watchlist = AddWatchlist()

    const val MOVIE_ID = 1
    val image = Image(id = MOVIE_ID, posters = "/poster.jpg")

    val exception = RuntimeException("error")

    val movie = Movie(
        id = 1,
        title = "Movie Title",
        overview = "Overview",
        posterPath = "/poster.jpg",
        backdropPath = "/backdrop.jpg",
        releaseDate = "2024-03-20",
        voteAverage = 7.5,
        voteCount = 100,
    )

    val pagingData = PagingData.from(listOf(movie))
}
