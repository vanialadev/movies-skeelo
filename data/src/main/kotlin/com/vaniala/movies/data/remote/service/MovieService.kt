package com.vaniala.movies.data.remote.service

import com.vaniala.movies.data.remote.model.ApiResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular?language=pt-BR")
    suspend fun getMoviePopular(@Query("page") page: Int): ApiResponse<MovieResponse>
}
