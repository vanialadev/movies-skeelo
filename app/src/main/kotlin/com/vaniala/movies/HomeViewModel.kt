package com.vaniala.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.usecase.GetMoviePopular
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class HomeViewModel @Inject constructor(private val getMoviePopular: GetMoviePopular) : ViewModel() {
    fun getMovies(): Flow<PagingData<Movie>> = getMoviePopular().cachedIn(viewModelScope)
}
