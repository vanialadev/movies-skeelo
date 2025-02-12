package com.vaniala.movies.data.remote.service

import com.vaniala.movies.data.remote.model.AddFavoriteRequest
import com.vaniala.movies.data.remote.model.AddWatchListOrFavoriteResponse
import com.vaniala.movies.data.remote.model.AddWatchlistRequest
import com.vaniala.movies.data.remote.model.ApiResponse
import com.vaniala.movies.data.remote.model.ImagesResponse
import com.vaniala.movies.data.remote.model.MovieDetailsResponse
import com.vaniala.movies.data.remote.model.MovieResponse
import com.vaniala.movies.data.remote.model.MovieStatusResponse
import com.vaniala.movies.data.remote.model.ProfileDetailsResponse
import com.vaniala.movies.data.utils.Constants.URL_ADD_FAVORITES_MOVIES
import com.vaniala.movies.data.utils.Constants.URL_ADD_WATCHLIST_MOVIES
import com.vaniala.movies.data.utils.Constants.URL_FAVORITE_MOVIES
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_DETAILS
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_IMAGES
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_POPULAR
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_RECOMMENDATIONS
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_SEARCH
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_STATUS
import com.vaniala.movies.data.utils.Constants.URL_MOVIE_TOP_RATED
import com.vaniala.movies.data.utils.Constants.URL_PROFILE_DETAILS
import com.vaniala.movies.data.utils.Constants.URL_WATCHLIST_MOVIES
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET(URL_MOVIE_POPULAR)
    suspend fun getMoviePopular(@Query("page") page: Int): ApiResponse<MovieResponse>

    @GET(URL_MOVIE_IMAGES)
    suspend fun getMovieImages(@Path("movie_id") moveId: Int): ImagesResponse

    @GET(URL_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movie_id") moveId: Int): MovieDetailsResponse

    @GET(URL_MOVIE_STATUS)
    suspend fun getMovieStatus(@Path("movie_id") moveId: Int): MovieStatusResponse

    @GET(URL_MOVIE_TOP_RATED)
    suspend fun getMovieTopRated(@Query("page") page: Int): ApiResponse<MovieResponse>

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

    @POST(URL_ADD_FAVORITES_MOVIES)
    suspend fun addFavorite(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body body: AddFavoriteRequest,
    ): AddWatchListOrFavoriteResponse

    @POST(URL_ADD_WATCHLIST_MOVIES)
    suspend fun addWatchlist(
        @Path("account_id") accountId: Int,
        @Query("session_id") sessionId: String,
        @Body body: AddWatchlistRequest,
    ): AddWatchListOrFavoriteResponse

    @GET(URL_MOVIE_RECOMMENDATIONS)
    suspend fun getMovieRecommendations(@Path("movie_id") movieId: Int): ApiResponse<MovieResponse>

    @GET(URL_MOVIE_SEARCH)
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): ApiResponse<MovieResponse>
}
