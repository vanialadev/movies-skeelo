package com.vaniala.movies.domain.model

data class MovieDetails(
    var backdropPath: String? = null,
    var genres: List<Genres>? = null,
    var id: Int? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null,
)
