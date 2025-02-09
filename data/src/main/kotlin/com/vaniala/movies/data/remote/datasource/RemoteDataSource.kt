package com.vaniala.movies.data.remote.datasource

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getMoviePopular(): Flow<PagingData<Movie>>
    fun getMovieImages(moveId: Int): Flow<Image>
}
