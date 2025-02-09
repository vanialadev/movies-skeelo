package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMovieImages @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(movieId: Int): Flow<Image> = repository.getMovieImages(movieId)
}
