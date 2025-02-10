package com.vaniala.movies.data.mappers

import com.vaniala.movies.data.remote.model.AddFavoriteRequest
import com.vaniala.movies.data.remote.model.AddWatchListOrFavoriteResponse
import com.vaniala.movies.data.remote.model.AddWatchlistRequest
import com.vaniala.movies.data.remote.model.AvatarResponse
import com.vaniala.movies.data.remote.model.GenresResponse
import com.vaniala.movies.data.remote.model.GravatarResponse
import com.vaniala.movies.data.remote.model.ImagesResponse
import com.vaniala.movies.data.remote.model.MovieDetailsResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.model.MovieStatusResponse
import com.vaniala.movies.data.remote.model.ProfileDetailsResponse
import com.vaniala.movies.data.remote.model.TmdbResponse
import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.model.AddWatchListOrFavorite
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.model.Avatar
import com.vaniala.movies.domain.model.Genres
import com.vaniala.movies.domain.model.Gravatar
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.MovieDetails
import com.vaniala.movies.domain.model.MovieStatus
import com.vaniala.movies.domain.model.ProfileDetails
import com.vaniala.movies.domain.model.Tmdb

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

    fun AddWatchListOrFavoriteResponse.toModel() = AddWatchListOrFavorite(
        success = success,
        statusMessage = statusMessage,
    )

    fun AddWatchlist.toRequest() = AddWatchlistRequest(
        mediaType = mediaType,
        mediaId = mediaId,
        watchlist = watchlist,
    )

    fun AddFavorite.toRequest() = AddFavoriteRequest(
        mediaType = mediaType,
        mediaId = mediaId,
        favorite = favorite,
    )

    fun MovieStatusResponse.toModel() = MovieStatus(
        id = id,
        favorite = favorite,
        rated = rated,
        watchlist = watchlist,
    )
}
