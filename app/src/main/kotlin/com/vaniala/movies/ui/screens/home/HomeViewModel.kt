package com.vaniala.movies.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.usecase.GetMoviePopular
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMoviePopular: GetMoviePopular) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private fun getMovies(): Flow<PagingData<Movie>> = getMoviePopular().cachedIn(viewModelScope)

    init {
        _uiState.update { state ->
            state.copy(moviesPagingData = getMovies())
        }
    }
}
