package com.vaniala.movies.ui.screens.search

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class SearchUiState(
    val query: String = "",
    val searchResults: Flow<PagingData<Movie>>? = null,
    val showMinimumCharactersMessage: Boolean = false,
)
