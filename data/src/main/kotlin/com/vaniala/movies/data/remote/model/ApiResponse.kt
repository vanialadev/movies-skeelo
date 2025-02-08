package com.vaniala.movies.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<out T : Any>(
    val page: Long? = null,
    val results: List<T>? = null,
    @Json(name = "total_pages")
    val totalPages: Long? = null,
    @Json(name = "total_results")
    val totalResults: Long? = null,
)
