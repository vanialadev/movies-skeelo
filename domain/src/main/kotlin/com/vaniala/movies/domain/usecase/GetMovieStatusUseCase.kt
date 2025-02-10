package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieStatusUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(moveId: Int) = repository.getMovieStatus(moveId)
}
