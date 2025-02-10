package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.model.MovieAllDetails
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip

class GetAllMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(moveId: Int): Flow<MovieAllDetails> {
        val movieDetailsFlow = repository.getMovieDetails(moveId)
        val movieStatusFlow = repository.getMovieStatus(moveId)

        return movieDetailsFlow.zip(movieStatusFlow) { movieDetails, movieStatus ->
            MovieAllDetails(movieDetails, movieStatus)
        }
    }
}
