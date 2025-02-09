package com.vaniala.movies.data.remote.model.profile

data class ProfileDetailsResponse(
    var id: Int? = null,
    var avatar: AvatarResponse? = null,
    var name: String? = null,
    var username: String? = null,
)
