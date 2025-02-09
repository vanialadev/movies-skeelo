package com.vaniala.movies.data.mappers

import com.vaniala.movies.data.remote.model.ImagesResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import kotlin.random.Random

object Mappers {
    fun MovieResponse.toModel() = Movie(
        adult = adult ?: false,
        backdropPath = backdropPath ?: String(),
        genreIds = genreIds ?: emptyList(),
        id = id ?: Random.nextLong(),
        originalLanguage = originalLanguage ?: String(),
        originalTitle = originalTitle ?: String(),
        overview = overview ?: String(),
        popularity = popularity ?: 0.0,
        posterPath = posterPath ?: String(),
        releaseDate = releaseDate ?: String(),
        title = title ?: String(),
        video = video ?: false,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0,
    )

    fun ImagesResponse.toModel(): Image {
        val image = Image(
            id = id ?: 0,
            posters = posters?.first()?.filePath ?: String(),
        )
        return image
    }
}
