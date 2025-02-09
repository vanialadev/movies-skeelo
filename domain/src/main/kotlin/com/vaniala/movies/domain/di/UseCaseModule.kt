package com.vaniala.movies.domain.di

import com.vaniala.movies.domain.repository.MovieRepository
import com.vaniala.movies.domain.usecase.GetMovieImages
import com.vaniala.movies.domain.usecase.GetMoviePopular
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
    fun providesGetMoviePopular(repository: MovieRepository): GetMoviePopular = GetMoviePopular(repository)

    @Singleton
    @Provides
    fun providesGetMovieImages(repository: MovieRepository): GetMovieImages = GetMovieImages(repository)
}
