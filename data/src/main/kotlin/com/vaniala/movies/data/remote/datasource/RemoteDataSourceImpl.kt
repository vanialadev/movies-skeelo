package com.vaniala.movies.data.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.vaniala.movies.data.mappers.Mappers.toModel
import com.vaniala.movies.data.remote.paging.MoviePaging
import com.vaniala.movies.data.remote.service.MovieService
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

private const val PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE = 15

class RemoteDataSourceImpl @Inject constructor(private val movieService: MovieService) : RemoteDataSource {
    override fun getMoviePopular(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = INITIAL_LOAD_SIZE,
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
}
