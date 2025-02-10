package com.vaniala.movies.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaniala.movies.domain.model.profile.ProfileDetails
import com.vaniala.movies.domain.usecase.GetProfileDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(private val getProfileDetailsUseCase: GetProfileDetailsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        getProfileDetails()
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
