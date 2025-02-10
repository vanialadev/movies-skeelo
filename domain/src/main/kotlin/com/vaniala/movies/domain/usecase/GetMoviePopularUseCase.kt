package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviePopularUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke() = repository.getMoviePopular()
}
