package com.vaniala.movies.data.utils

object Constants {

    const val STARTING_PAGE_INDEX = 1

    const val URL_MOVIE_POPULAR = "movie/popular?language=pt-BR"
    const val URL_MOVIE_IMAGES = "movie/{movie_id}/images"
    const val URL_MOVIE_DETAILS = "movie/{movie_id}?language=pt-BR"

    const val URL_PROFILE_DETAILS = "account/{account_id}"

    const val URL_FAVORITE_MOVIES = "account/{account_id}/favorite/movies?language=pt-BR&page=1&sort_by=created_at.asc"
    const val URL_WATCHLIST_MOVIES = "account/{account_id}/watchlist/movies?language=pt-br&page=1&sort_by=created_at.asc"

    const val URL_ADD_FAVORITES_MOVIES = "account/{account_id}/favorite}"
    const val URL_ADD_WATCHLIST_MOVIES = "account/{account_id}/watchlist}"
}
