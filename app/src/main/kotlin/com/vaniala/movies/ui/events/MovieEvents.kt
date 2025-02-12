package com.vaniala.movies.ui.events

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object MovieEvents {
    private val _favoriteUpdated = MutableSharedFlow<Int>()
    val favoriteUpdated = _favoriteUpdated.asSharedFlow()

    private val _watchlistUpdated = MutableSharedFlow<Int>()
    val watchlistUpdated = _watchlistUpdated.asSharedFlow()

    suspend fun notifyFavoriteUpdated(movieId: Int) {
        _favoriteUpdated.emit(movieId)
    }

    suspend fun notifyWatchlistUpdated(movieId: Int) {
        _watchlistUpdated.emit(movieId)
    }
}
