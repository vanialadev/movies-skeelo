package com.vaniala.movies.ui.screens.home

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class HomeUiState(val moviesPagingData: Flow<PagingData<Movie>>? = null)
