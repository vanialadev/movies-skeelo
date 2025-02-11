package com.vaniala.movies.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieStatusResponse(
    var id: Int? = null,
    var favorite: Boolean = false,
    var rated: Boolean = false,
    var watchlist: Boolean = false,
)
