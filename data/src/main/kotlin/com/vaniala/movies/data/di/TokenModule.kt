package com.vaniala.movies.data.di

import com.vaniala.movies.data.interceptor.TokenProvider
import com.vaniala.movies.data.interceptor.TokenProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenModule {
    @Binds
    abstract fun bindTokenProvider(tokenProviderImpl: TokenProviderImpl): TokenProvider
}
