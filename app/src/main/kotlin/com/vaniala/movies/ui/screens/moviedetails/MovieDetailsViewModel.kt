package com.vaniala.movies.ui.screens.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.model.AddWatchListOrFavorite
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.usecase.AddFavoriteUseCase
import com.vaniala.movies.domain.usecase.AddWatchlistUseCase
import com.vaniala.movies.domain.usecase.GetAllMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getAllMovieDetailsUseCase: GetAllMovieDetailsUseCase,
    private val addToFavoriteUseCase: AddFavoriteUseCase,
    private val addWatchlistUseCase: AddWatchlistUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getAllMovieDetailsUseCase(id)
                .collect { movieAllDetails ->
                    _uiState.value = _uiState.value.copy(movieAllDetails = movieAllDetails)
                    Timber.d("$movieAllDetails")
                }
        }
    }

    fun toggleFavorite(id: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            addToFavoriteUseCase(AddFavorite(mediaId = id, favorite = isFavorite))
                .collect { status: AddWatchListOrFavorite ->
                    if (status.success == true) {
                        _uiState.value = _uiState.value.copy(
                            movieAllDetails = _uiState.value.movieAllDetails?.copy(
                                movieStatus = _uiState.value.movieAllDetails?.movieStatus?.copy(
                                    favorite = isFavorite,
                                ),
                            ),
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            messageError = status.statusMessage,
                        )
                        Timber.e("Status: $status")
                    }
                }
        }
    }

    fun toggleWatchlist(id: Int, isWatchlist: Boolean) {
        viewModelScope.launch {
            addWatchlistUseCase(AddWatchlist(mediaId = id, watchlist = isWatchlist))
                .collect { status: AddWatchListOrFavorite ->
                    if (status.success == true) {
                        _uiState.value = _uiState.value.copy(
                            movieAllDetails = _uiState.value.movieAllDetails?.copy(
                                movieStatus = _uiState.value.movieAllDetails?.movieStatus?.copy(
                                    watchlist = isWatchlist,
                                ),
                            ),
                        )
                    } else {
                        _uiState.value = _uiState.value.copy(
                            messageError = status.statusMessage,
                        )
                        Timber.e("Status: $status")
                    }
                }
        }
    }
}
