package com.vaniala.movies.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.usecase.GetMovieImagesUseCase
import com.vaniala.movies.domain.usecase.GetMoviePopularUseCase
import com.vaniala.movies.domain.usecase.GetMovieTopRatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import timber.log.Timber

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviePopularUseCase: GetMoviePopularUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val getMovieTopRatedUseCase: GetMovieTopRatedUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovies()
        getTopRatedMovies()
    }

    private fun getMovies() {
        val moviesWithImages: Flow<PagingData<Movie>> = getMoviePopularUseCase()
            .map { pagingData ->
                pagingData.map { movie ->
                    try {
                        val image = movie.id?.toInt()?.let { getMovieImagesUseCase(it).first() }
                        movie.copy(images = image)
                    } catch (e: Exception) {
                        Timber.e(" ${movie.id}: $e")
                        movie.copy(images = Image())
                    }
                }
            }
            .cachedIn(viewModelScope)

        _uiState.update { state ->
            state.copy(popularMoviesPagingData = moviesWithImages)
        }
    }

    private fun getTopRatedMovies() {
        val moviesWithImages: Flow<PagingData<Movie>> = getMovieTopRatedUseCase()
            .map { pagingData ->
                pagingData.map { movie ->
                    try {
                        val image = movie.id?.toInt()?.let { getMovieImagesUseCase(it).first() }
                        movie.copy(images = image)
                    } catch (e: Exception) {
                        Timber.e(" ${movie.id}: $e")
                        movie.copy(images = Image())
                    }
                }
            }
            .cachedIn(viewModelScope)

        _uiState.update { state ->
            state.copy(topRatedMoviesPagingData = moviesWithImages)
        }
    }
}
