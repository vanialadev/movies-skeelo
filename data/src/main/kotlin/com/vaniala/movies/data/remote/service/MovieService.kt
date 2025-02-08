package com.vaniala.movies.data.remote.service

import com.vaniala.movies.data.remote.model.Response
import retrofit2.http.GET

interface MovieService {

    @GET("movie/popular?language=pt-BR")
    suspend fun getMoviePopular(): Response
}