package com.vaniala.movies.data.remote.model.profile

data class AvatarResponse(var gravatar: GravatarResponse? = GravatarResponse(), var tmdb: TmdbResponse? = TmdbResponse())
