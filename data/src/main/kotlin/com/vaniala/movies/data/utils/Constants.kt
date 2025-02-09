package com.vaniala.movies.data.utils

object Constants {

    const val URL_MOVIE_POPULAR = "movie/popular?language=pt-BR"
    const val URL_MOVIE_IMAGES = "movie/{movie_id}/images"

    const val URL_PROFILE_DETAILS = "account/{account_id}"

    const val URL_FAVORITE_MOVIES =
        "account/{account_id}/favorite/movies?language=pt-BR&session_id={session_id}&sort_by=created_at.asc"

    const val URL_ADD_FAVORITES_MOVIES = "account/{account_id}/favorite?session_id={session_id}"
}
