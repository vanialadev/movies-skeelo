package com.vaniala.movies.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddWatchListOrFavoriteResponse(
    val success: Boolean? = null,
    @Json(name = "status_message")
    val statusMessage: String? = null,
)
