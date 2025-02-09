package com.vaniala.movies.data.mappers

import com.vaniala.movies.data.remote.model.ImagesResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.model.profile.DetailsProfileResponse
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.profile.Avatar
import com.vaniala.movies.domain.model.profile.DetailsProfile
import com.vaniala.movies.domain.model.profile.Gravatar
import com.vaniala.movies.domain.model.profile.Tmdb
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

    fun ImagesResponse.toModel(): Image = Image(
        id = id ?: 0,
        posters = posters?.first()?.filePath ?: String(),
    )

    fun DetailsProfileResponse.toModel() = DetailsProfile(
        id = id ?: 0,
        avatar = avatar ?: Avatar(
            gravatar = Gravatar(String()),
            tmdb = Tmdb(String()),
        ),
        name = name ?: String(),
        username = username ?: String(),
    )
}
