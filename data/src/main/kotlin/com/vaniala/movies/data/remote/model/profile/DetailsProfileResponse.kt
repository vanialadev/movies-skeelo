package com.vaniala.movies.data.remote.model.profile

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsProfileResponse(
    var id: Int? = null,
    var avatar: Avatar? = null,
    var name: String? = null,
    var username: String? = null,
)
