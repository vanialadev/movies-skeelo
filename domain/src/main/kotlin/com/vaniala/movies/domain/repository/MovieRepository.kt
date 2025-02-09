package com.vaniala.movies.domain.repository

import androidx.paging.PagingData
import com.vaniala.movies.domain.model.Image
import com.vaniala.movies.domain.model.Movie
import com.vaniala.movies.domain.model.profile.DetailsProfile
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMoviePopular(): Flow<PagingData<Movie>>
    fun getMovieImages(moveId: Int): Flow<Image>
    fun getProfileDetails(): Flow<DetailsProfile>
}
