package com.vaniala.movies.domain.model

data class MovieStatus(
    var id: Int? = null,
    var favorite: Boolean = false,
    var rated: Boolean = false,
    var watchlist: Boolean = false,
)
