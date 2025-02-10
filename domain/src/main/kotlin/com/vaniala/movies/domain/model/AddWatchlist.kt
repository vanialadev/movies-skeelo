package com.vaniala.movies.domain.model

data class AddWatchlist(val mediaType: String = "movie", val mediaId: Int? = null, val watchlist: Boolean = false)
