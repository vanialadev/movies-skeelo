package com.vaniala.movies.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.profile.ProfileDetails
import com.vaniala.movies.domain.usecase.GetFavoriteUseCase
import com.vaniala.movies.domain.usecase.GetMovieImages
import com.vaniala.movies.domain.usecase.GetProfileDetailsUseCase
import com.vaniala.movies.domain.usecase.GetWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileDetailsUseCase: GetProfileDetailsUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val getWatchlistUseCase: GetWatchlistUseCase,
    private val getMovieImages: GetMovieImages,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        getProfileDetails()
        getFavorites()
        getWatchlist()
    }

    private fun getWatchlist() {
        val moviesWithImages: Flow<PagingData<Movie>> = getWatchlistUseCase().map { pagingData ->
            pagingData.map { movie ->
                try {
                    val image = movie.id?.toInt()?.let { getMovieImages(it).first() }
                    movie.copy(images = image)
                } catch (e: Exception) {
                    Timber.e(" ${movie.id}: $e")
                    movie.copy(images = Image())
                }
            }
        }.cachedIn(viewModelScope)

        _uiState.update { state ->
            state.copy(watchlistPagingData = moviesWithImages)
        }
    }

    private fun getFavorites() {
        val moviesWithImages: Flow<PagingData<Movie>> = getFavoriteUseCase().map { pagingData ->
            pagingData.map { movie ->
                try {
                    val image = movie.id?.toInt()?.let { getMovieImages(it).first() }
                    movie.copy(images = image)
                } catch (e: Exception) {
                    Timber.e(" ${movie.id}: $e")
                    movie.copy(images = Image())
                }
            }
        }.cachedIn(viewModelScope)

        _uiState.update { state ->
            state.copy(favoritesPagingData = moviesWithImages)
        }
    }

    private fun getProfileDetails() {
        viewModelScope.launch {
            getProfileDetailsUseCase().collect { profileDetails ->
                val name = getName(profileDetails)
                _uiState.update { state ->
                    state.copy(
                        profileDetails = profileDetails,
                        nameUpdate = name,
                    )
                }
            }
        }
    }

    private fun getName(profileDetails: ProfileDetails?): String? {
        var name: String? = null
        if (profileDetails?.username?.isNotEmpty() == true) {
            name = profileDetails.username
        } else if (profileDetails?.name?.isNotEmpty() == true) {
            name = profileDetails.name
        }
        return name
    }
}
