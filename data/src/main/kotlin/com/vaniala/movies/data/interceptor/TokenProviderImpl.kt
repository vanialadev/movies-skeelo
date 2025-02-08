package com.vaniala.movies.data.interceptor

import javax.inject.Inject

class TokenProviderImpl @Inject constructor() : TokenProvider {
    override fun getToken(): String = ""
}
