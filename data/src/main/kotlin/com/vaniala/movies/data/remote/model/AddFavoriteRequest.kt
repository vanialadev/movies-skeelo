package com.vaniala.movies.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddFavoriteRequest(
    @Json(name = "media_type")
    val mediaType: String = "movie",
    @Json(name = "media_id")
    val mediaId: Long? = null,
    val favorite: Boolean? = false,
)
