package com.vaniala.movies.data.remote.model.profile

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tmdb(
    @Json(name = "avatar_path")
    var avatarPath: String? = null,
)
