package com.vaniala.movies.ui.screens.profile

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.profile.ProfileDetails
import kotlinx.coroutines.flow.Flow

data class ProfileUiState(
    val profileDetails: ProfileDetails? = null,
    val nameUpdate: String? = null,
    val favoritesPagingData: Flow<PagingData<Movie>>? = null,
    val watchlistPagingData: Flow<PagingData<Movie>>? = null,
)
