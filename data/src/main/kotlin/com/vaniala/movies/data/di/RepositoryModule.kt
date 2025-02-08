package com.vaniala.movies.data.di

import com.vaniala.movies.data.remote.datasource.RemoteDataSource
import com.vaniala.movies.data.repository.MovieRepositoryImpl
import com.vaniala.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepositoryImpl(dataSource: RemoteDataSource): MovieRepository = MovieRepositoryImpl(dataSource)
}
