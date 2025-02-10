package com.vaniala.movies.data.remote.model

data class AvatarResponse(var gravatar: GravatarResponse? = GravatarResponse(), var tmdb: TmdbResponse? = TmdbResponse())
