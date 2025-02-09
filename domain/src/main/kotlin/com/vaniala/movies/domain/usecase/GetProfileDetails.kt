package com.vaniala.movies.domain.usecase

import com.vaniala.movies.domain.model.profile.DetailsProfile
import com.vaniala.movies.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProfileDetails @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<DetailsProfile> = repository.getProfileDetails()
}
