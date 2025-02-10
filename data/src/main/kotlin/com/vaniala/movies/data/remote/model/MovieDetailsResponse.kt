package com.vaniala.movies.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    @Json(name = "backdrop_path")
    var backdropPath: String? = null,
    var genres: List<GenresResponse>? = null,
    var id: Int? = null,
    @Json(name = "original_title")
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null,
    @Json(name = "tagline")
    var tagline: String? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "video")
    var video: Boolean? = null,
    @Json(name = "vote_average")
    var voteAverage: Double? = null,
    @Json(name = "vote_count")
    var voteCount: Int? = null,
)
