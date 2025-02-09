package com.vaniala.movies.data.interceptor

import com.vaniala.movies.data.BuildConfig
import javax.inject.Inject

class TokenProviderImpl @Inject constructor() : TokenProvider {
    override fun getToken(): String = BuildConfig.API_KEY
}
