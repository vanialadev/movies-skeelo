package com.vaniala.movies.domain.repository

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.model.AddWatchListOrFavorite
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.MovieDetails
import com.vaniala.movies.domain.model.MovieStatus
import com.vaniala.movies.domain.model.ProfileDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMoviePopular(): Flow<PagingData<Movie>>
    fun getMovieImages(moveId: Int): Flow<Image>
    fun getMovieDetails(moveId: Int): Flow<MovieDetails>
    fun getMovieStatus(moveId: Int): Flow<MovieStatus>
    fun getProfileDetails(): Flow<ProfileDetails>
    fun getFavorites(): Flow<PagingData<Movie>>
    fun getWatchlist(): Flow<PagingData<Movie>>
    fun addFavorites(favorite: AddFavorite): Flow<AddWatchListOrFavorite>
    fun addWatchlist(watchlist: AddWatchlist): Flow<AddWatchListOrFavorite>
    fun getMovieRecommendations(movieId: Int): Flow<List<Movie>>
}
