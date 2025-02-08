package com.vaniala.movies.data.interceptor

interface TokenProvider {
    fun getToken(): String
}
