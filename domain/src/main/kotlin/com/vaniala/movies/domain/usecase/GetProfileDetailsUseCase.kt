package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.model.profile.ProfileDetails
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProfileDetailsUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<ProfileDetails> = repository.getProfileDetails()
}
