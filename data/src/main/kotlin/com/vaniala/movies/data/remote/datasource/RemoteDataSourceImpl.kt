package com.vaniala.movies.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.vaniala.movies.data.BuildConfig
import com.vaniala.movies.data.mappers.Mappers.toModel
import com.vaniala.movies.data.mappers.Mappers.toRequest
import com.vaniala.movies.data.remote.paging.FavoritePaging
import com.vaniala.movies.data.remote.paging.MoviePaging
import com.vaniala.movies.data.remote.paging.WatchlistPaging
import com.vaniala.movies.data.remote.service.MovieService
import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.model.AddWatchListOrFavorite
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.MovieDetails
import com.vaniala.movies.domain.model.ProfileDetails
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE_MOVIE_POPULAR = 10
private const val INITIAL_LOAD_SIZE_MOVIE_POPULAR = 15

private const val PAGE_SIZE_PROFILE = 5
private const val INITIAL_LOAD_SIZE_PROFILE = 10

class RemoteDataSourceImpl @Inject constructor(private val movieService: MovieService) : RemoteDataSource {
    override fun getMoviePopular(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE_MOVIE_POPULAR,
            enablePlaceholders = false,
            initialLoadSize = INITIAL_LOAD_SIZE_MOVIE_POPULAR,
        ),
        pagingSourceFactory = {
            MoviePaging(movieService)
        },
    ).flow
        .flowOn(IO)
        .map { paging ->
            paging.map { movies ->
                movies.toModel()
            }
        }

    override fun getMovieImages(moveId: Int): Flow<Image> = flow {
        emit(movieService.getMovieImages(moveId).toModel())
    }.flowOn(IO)

    override fun getProfileDetails(): Flow<ProfileDetails> = flow {
        val accountId = BuildConfig.ACOUNT_ID
        emit(movieService.getProfileDetails(accountId).toModel())
    }.flowOn(IO)

    override fun getFavorites(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE_PROFILE,
            enablePlaceholders = false,
            initialLoadSize = INITIAL_LOAD_SIZE_PROFILE,
        ),
        pagingSourceFactory = {
            FavoritePaging(movieService)
        },
    ).flow
        .flowOn(IO)
        .map { paging ->
            paging.map { movies ->
                movies.toModel()
            }
        }

    override fun getWatchlist(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE_PROFILE,
            enablePlaceholders = false,
            initialLoadSize = INITIAL_LOAD_SIZE_PROFILE,
        ),
        pagingSourceFactory = {
            WatchlistPaging(movieService)
        },
    ).flow
        .flowOn(IO)
        .map { paging ->
            paging.map { movies ->
                movies.toModel()
            }
        }

    override fun getMovieDetails(moveId: Long): Flow<MovieDetails> = flow {
        emit(movieService.getMovieDetails(moveId).toModel())
    }.flowOn(IO)

    override fun addFavorites(favorite: AddFavorite): Flow<AddWatchListOrFavorite> = flow {
        val addWatchListOrFavoriteResponse = movieService.addFavorite(
            accountId = BuildConfig.ACOUNT_ID,
            sessionId = BuildConfig.SESSION_ID,
            body = favorite.toRequest(),
        )
        emit(addWatchListOrFavoriteResponse.toModel())
    }.flowOn(IO)

    override fun addWatchlist(watchlist: AddWatchlist): Flow<AddWatchListOrFavorite> = flow {
        val addWatchListOrFavoriteResponse = movieService.addWatchlist(
            accountId = BuildConfig.ACOUNT_ID,
            sessionId = BuildConfig.SESSION_ID,
            body = watchlist.toRequest(),
        )
        emit(addWatchListOrFavoriteResponse.toModel())
    }.flowOn(IO)
}
