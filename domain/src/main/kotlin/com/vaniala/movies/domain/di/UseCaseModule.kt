package com.vaniala.movies.domain.di

import com.vaniala.movies.domain.repository.MovieRepository
import com.vaniala.movies.domain.usecase.GetMovieImagesUseCase
import com.vaniala.movies.domain.usecase.GetMoviePopularUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesGetMoviePopular(repository: MovieRepository): GetMoviePopularUseCase = GetMoviePopularUseCase(
        repository,
    )

    @Singleton
    @Provides
    fun providesGetMovieImages(repository: MovieRepository): GetMovieImagesUseCase = GetMovieImagesUseCase(repository)
}
