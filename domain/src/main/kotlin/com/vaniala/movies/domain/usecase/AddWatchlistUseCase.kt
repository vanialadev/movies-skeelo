package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject

class AddWatchlistUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(watchlist: AddWatchlist) = repository.addWatchlist(watchlist)
}
