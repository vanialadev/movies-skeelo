package com.vaniala.movies.ui.screens.profile

import com.vaniala.movies.domain.model.profile.ProfileDetails

data class ProfileUiState(val profileDetails: ProfileDetails? = null, val nameUpdate: String? = null)
