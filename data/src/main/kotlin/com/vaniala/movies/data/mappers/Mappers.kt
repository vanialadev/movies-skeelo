package com.vaniala.movies.data.mappers

import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.domain.model.Movie

object Mappers {
    fun MovieResponse.toModel() = Movie(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
