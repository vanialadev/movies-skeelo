package com.vaniala.movies.data.remote.model

data class Response(
    val page: Long,
    val results: List<Result>,
    val totalPages: Long,
    val totalResults: Long,
)

data class Result(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Long>,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long,
)
