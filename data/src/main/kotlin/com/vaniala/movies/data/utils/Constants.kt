package com.vaniala.movies.data.utils

object Constants {

    const val STARTING_PAGE_INDEX = 1

    const val URL_MOVIE_POPULAR = "movie/popular?language=pt-BR"
    const val URL_MOVIE_IMAGES = "movie/{movie_id}/images"
    const val URL_MOVIE_DETAILS = "movie/{movie_id}?language=pt-BR"
    const val URL_MOVIE_STATUS = "movie/{movie_id}/account_states"
    const val URL_MOVIE_RECOMMENDATIONS = "movie/{movie_id}/recommendations?language=pt-BR"
    const val URL_MOVIE_TOP_RATED = "movie/top_rated?language=pt-BR"
    const val URL_MOVIE_SEARCH = "search/movie?language=pt-BR&include_adult=false"

    const val URL_PROFILE_DETAILS = "account/{account_id}"

    const val URL_FAVORITE_MOVIES = "account/{account_id}/favorite/movies?language=pt-BR&page=1&sort_by=created_at.desc"
    const val URL_WATCHLIST_MOVIES = "account/{account_id}/watchlist/movies?language=pt-br&page=1&sort_by=created_at.desc"

    const val URL_ADD_FAVORITES_MOVIES = "account/{account_id}/favorite"
    const val URL_ADD_WATCHLIST_MOVIES = "account/{account_id}/watchlist"
}
