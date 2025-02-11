package com.vaniala.movies.data

import com.vaniala.movies.data.remote.model.AddWatchListOrFavoriteResponse
import com.vaniala.movies.data.remote.model.ApiResponse
import com.vaniala.movies.data.remote.model.MovieDetailsResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.MovieDetails
object TestData {
    val movieDetailsResponse = MovieDetailsResponse(
        id = 1,
        title = "title",
        overview = "overviewe",
        posterPath = "/poster.jpg",
        backdropPath = "/backdrop.jpg",
    )

    private val movieResponse = MovieResponse(
        id = 1,
        title = "title",
        overview = "overviewe",
        voteAverage = 8.5,
        posterPath = "/poster.jpg",
        backdropPath = "/backdrop.jpg",
    )
    private val movie = Movie(
        id = 1,
        title = "title",
        overview = "overviewe",
        voteAverage = 8.5,
        posterPath = "/poster.jpg",
        backdropPath = "/backdrop.jpg",
    )

    val listMovie = listOf(movie)

    val movieDetails = MovieDetails(
        id = 1,
        title = "title",
        overview = "overview",
    )

    val moviePopularResponse = ApiResponse(
        page = 1,
        results = listOf(movieResponse),
        totalPages = 10,
        totalResults = 100,
    )

    val moviePopularEmptyResponse: ApiResponse<MovieResponse> = ApiResponse(
        page = 1,
        results = emptyList(),
        totalPages = 0,
        totalResults = 0,
    )

    val addWatchListOrFavoriteResponse = AddWatchListOrFavoriteResponse(
        success = true,
        statusMessage = "success",
    )
}
