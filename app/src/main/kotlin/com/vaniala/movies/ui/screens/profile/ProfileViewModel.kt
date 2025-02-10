package com.vaniala.movies.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.vaniala.movies.domain.model.AddFavorite
import com.vaniala.movies.domain.model.AddWatchListOrFavorite
import com.vaniala.movies.domain.model.AddWatchlist
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.ProfileDetails
import com.vaniala.movies.domain.usecase.AddFavoriteUseCase
import com.vaniala.movies.domain.usecase.AddWatchlistUseCase
import com.vaniala.movies.domain.usecase.GetFavoriteUseCase
import com.vaniala.movies.domain.usecase.GetMovieImagesUseCase
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
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val addToFavoriteUseCase: AddFavoriteUseCase,
    private val addWatchlistUseCase: AddWatchlistUseCase,
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
                    val image = movie.id?.toInt()?.let { getMovieImagesUseCase(it).first() }
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
                    val image = movie.id?.toInt()?.let { getMovieImagesUseCase(it).first() }
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

    fun removeFavorite(id: Int) {
        viewModelScope.launch {
            addToFavoriteUseCase(AddFavorite(mediaId = id, favorite = false))
                .collect { status: AddWatchListOrFavorite ->
                    if (status.success == true) {
                        _uiState.update { state ->
                            state.copy(
                                favoritesPagingData = state.favoritesPagingData?.map { pagingData ->
                                    pagingData.filter { movie ->
                                        movie.id != id
                                    }
                                }?.cachedIn(viewModelScope),
                            )
                        }
                    }
                }
        }
    }

    fun removeWatchlist(id: Int) {
        viewModelScope.launch {
            addWatchlistUseCase(AddWatchlist(mediaId = id, watchlist = false))
                .collect { status: AddWatchListOrFavorite ->
                    if (status.success == true) {
                        _uiState.update { state ->
                            state.copy(
                                watchlistPagingData = state.watchlistPagingData?.map { pagingData ->
                                    pagingData.filter { movie ->
                                        movie.id?.toInt() != id
                                    }
                                }?.cachedIn(viewModelScope),
                            )
                        }
                    }
                }
        }
    }
}
