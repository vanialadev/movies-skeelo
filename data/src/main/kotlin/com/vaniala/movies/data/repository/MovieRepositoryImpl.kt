package com.vaniala.movies.data.repository

import androidx.paging.PagingData
import com.vaniala.movies.data.remote.datasource.RemoteDataSource
import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.model.AddWatchListOrFavorite
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.MovieDetails
import com.vaniala.movies.domain.model.MovieStatus
import com.vaniala.movies.domain.model.ProfileDetails
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) : MovieRepository {
    override fun getMoviePopular(): Flow<PagingData<Movie>> = dataSource.getMoviePopular().flowOn(IO)
    override fun getMovieImages(moveId: Int): Flow<Image> = dataSource.getMovieImages(moveId).flowOn(IO)
    override fun getMovieDetails(moveId: Int): Flow<MovieDetails> = dataSource.getMovieDetails(moveId).flowOn(IO)
    override fun getMovieStatus(moveId: Int): Flow<MovieStatus> = dataSource.getMovieStatus(moveId).flowOn(IO)
    override fun getProfileDetails(): Flow<ProfileDetails> = dataSource.getProfileDetails().flowOn(IO)
    override fun getFavorites(): Flow<PagingData<Movie>> = dataSource.getFavorites().flowOn(IO)
    override fun getWatchlist(): Flow<PagingData<Movie>> = dataSource.getWatchlist().flowOn(IO)
    override fun addFavorites(favorite: AddFavorite): Flow<AddWatchListOrFavorite> = dataSource.addFavorites(
        favorite,
    ).flowOn(IO)

    override fun addWatchlist(watchlist: AddWatchlist): Flow<AddWatchListOrFavorite> = dataSource.addWatchlist(
        watchlist,
    )

    override fun getMovieRecommendations(movieId: Int): Flow<List<Movie>> = dataSource.getMovieRecommendations(
        movieId,
    ).flowOn(IO)

    override fun getMovieTopRated(): Flow<PagingData<Movie>> = dataSource.getMovieTopRated().flowOn(IO)

    override fun searchMovies(query: String): Flow<PagingData<Movie>> = dataSource.searchMovies(query).flowOn(IO)
}
