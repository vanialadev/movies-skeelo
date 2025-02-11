package com.vaniala.movies.domain.usecase

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMovieTopRatedUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<PagingData<Movie>> = repository.getMovieTopRated()
}
