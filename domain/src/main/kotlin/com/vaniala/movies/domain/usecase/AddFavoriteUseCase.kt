package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(favorite: AddFavorite) = repository.addFavorites(favorite)
}
