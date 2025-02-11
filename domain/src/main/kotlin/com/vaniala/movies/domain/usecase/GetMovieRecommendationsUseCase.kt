package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMovieRecommendationsUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int): Flow<List<Movie>> = repository.getMovieRecommendations(movieId)
}
