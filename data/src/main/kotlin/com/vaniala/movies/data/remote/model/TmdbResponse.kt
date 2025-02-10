package com.vaniala.movies.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TmdbResponse(
    @Json(name = "avatar_path")
    var avatarPath: String? = null,
)
