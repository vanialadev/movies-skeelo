package com.vaniala.movies.data.mappers

import com.vaniala.movies.data.remote.model.GenresResponse
import com.vaniala.movies.data.remote.model.ImagesResponse
import com.vaniala.movies.data.remote.model.MovieDetailsResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.model.profile.AvatarResponse
import com.vaniala.movies.data.remote.model.profile.GravatarResponse
import com.vaniala.movies.data.remote.model.profile.ProfileDetailsResponse
import com.vaniala.movies.data.remote.model.profile.TmdbResponse
import com.vaniala.movies.domain.model.Genres
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.MovieDetails
import com.vaniala.movies.domain.model.profile.Avatar
import com.vaniala.movies.domain.model.profile.Gravatar
import com.vaniala.movies.domain.model.profile.ProfileDetails
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

    fun GravatarResponse.toModel() = Gravatar(
        hash = hash,
    )

    fun TmdbResponse.toModel() = Tmdb(
        avatarPath = avatarPath,
    )

    fun AvatarResponse.toModel() = Avatar(
        gravatar = gravatar?.toModel(),
        tmdb = tmdb?.toModel(),
    )

    fun ProfileDetailsResponse.toModel() = ProfileDetails(
        id = id,
        avatar = avatar?.toModel(),
        name = name,
        username = username,
    )

    fun MovieDetailsResponse.toModel(): MovieDetails = MovieDetails(
        backdropPath = backdropPath,
        genres = genres?.map { it.toGenres() },
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )

    private fun GenresResponse.toGenres(): Genres = Genres(
        id = id,
        name = name,
    )
}
