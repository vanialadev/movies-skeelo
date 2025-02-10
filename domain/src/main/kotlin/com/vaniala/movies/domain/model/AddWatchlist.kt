package com.vaniala.movies.domain.model

data class AddWatchlist(val mediaType: String = "movie", val mediaId: Long? = null, val favorite: Boolean? = false)
