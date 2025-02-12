package com.vaniala.movies.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.vaniala.movies.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val MINIMUM_QUERY_LENGTH = 4

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchMoviesUseCase: SearchMoviesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun onQueryChange(newQuery: String) {
        _uiState.update { state ->
            state.copy(
                query = newQuery,
                searchResults = if (newQuery.isNotEmpty()) {
                    searchMoviesUseCase(newQuery).cachedIn(viewModelScope)
                } else {
                    null
                },
                showMinimumCharactersMessage = newQuery.isNotEmpty() && newQuery.length < MINIMUM_QUERY_LENGTH,
            )
        }
    }
}
