package com.vaniala.movies.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PosterResponse(
    @Json(name = "file_path")
    var filePath: String? = null,
)
