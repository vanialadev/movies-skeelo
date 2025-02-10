package com.vaniala.movies.domain.model

data class AddFavorite(val mediaType: String? = "movie", val mediaId: Int? = null, val favorite: Boolean? = false)
