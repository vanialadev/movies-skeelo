package com.vaniala.movies.domain.usecase

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private const val MINIMUM_QUERY_LENGTH = 4

class SearchMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(query: String): Flow<PagingData<Movie>> {
        return if (query.length >= MINIMUM_QUERY_LENGTH) {
            repository.searchMovies(query)
        } else {
            flowOf(PagingData.empty())
        }
    }
}
