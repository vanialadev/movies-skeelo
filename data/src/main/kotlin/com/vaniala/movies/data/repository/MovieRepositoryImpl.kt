package com.vaniala.movies.data.repository

import androidx.paging.PagingData
import com.vaniala.movies.data.remote.datasource.RemoteDataSource
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource) : MovieRepository {
    override fun getMoviePopular(): Flow<PagingData<Movie>> = dataSource.getMoviePopular().flowOn(IO)
    override fun getMovieImages(moveId: Int): Flow<Image> = dataSource.getMovieImages(moveId).flowOn(IO)
}
