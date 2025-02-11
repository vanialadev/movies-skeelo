package com.vaniala.movies.di

import com.vaniala.movies.domain.repository.MovieRepository
import com.vaniala.movies.domain.usecase.GetMovieImagesUseCase
import com.vaniala.movies.domain.usecase.GetMoviePopularUseCase
import com.vaniala.movies.domain.usecase.GetMovieTopRatedUseCase
import com.vaniala.movies.preferences.ThemePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mockk.mockk
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(): MovieRepository = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(repository: MovieRepository): GetMoviePopularUseCase = GetMoviePopularUseCase(
        repository,
    )

    @Provides
    @Singleton
    fun provideThemePreferences(): ThemePreferences = mockk(relaxed = true)

    @Provides
    @Singleton
    fun provideGetMovieImagesUseCase(repository: MovieRepository): GetMovieImagesUseCase = GetMovieImagesUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMovieTopRatedUseCase(repository: MovieRepository): GetMovieTopRatedUseCase = GetMovieTopRatedUseCase(repository)
}
