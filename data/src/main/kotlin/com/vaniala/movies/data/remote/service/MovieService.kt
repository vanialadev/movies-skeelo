package com.vaniala.movies.data.remote.service

import com.vaniala.movies.data.remote.model.ApiResponse
import com.vaniala.movies.data.remote.model.ImagesResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.model.profile.ProfileDetailsResponse
import com.vaniala.movies.data.utils.Constants.URL_FAVORITE_MOVIES
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_IMAGES
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_POPULAR
import com.vaniala.movies.data.utils.Constants.URL_PROFILE_DETAILS
import com.vaniala.movies.data.utils.Constants.URL_WATCHLIST_MOVIES
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(URL_MOVIE_POPULAR)
    suspend fun getMoviePopular(@Query("page") page: Int): ApiResponse<MovieResponse>

    @GET(URL_MOVIE_IMAGES)
    suspend fun getMovieImages(@Path("movie_id") moveId: Int): ImagesResponse

    @GET(URL_PROFILE_DETAILS)
    suspend fun getProfileDetails(@Path("account_id") accountId: Int): ProfileDetailsResponse

    @GET(URL_FAVORITE_MOVIES)
    suspend fun getFavorites(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("page") page: Int,
    ): ApiResponse<MovieResponse>

    @GET(URL_WATCHLIST_MOVIES)
    suspend fun getWatchlist(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Query("page") page: Int,
    ): ApiResponse<MovieResponse>
}
