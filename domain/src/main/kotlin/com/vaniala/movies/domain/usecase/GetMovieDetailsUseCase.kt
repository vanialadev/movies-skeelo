package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(moveId: Long) = repository.getMovieDetails(moveId)
}
