package com.vaniala.movies.data.di

import com.vaniala.movies.data.remote.datasource.RemoteDataSource
import com.vaniala.movies.data.remote.datasource.RemoteDataSourceImpl
import com.vaniala.movies.data.remote.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(service: MovieService): RemoteDataSource = RemoteDataSourceImpl(service)
}
