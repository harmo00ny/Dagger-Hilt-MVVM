package com.marysugar.dagger_hilt_mvvm.di

import com.marysugar.dagger_hilt_mvvm.network.BlogApi
import com.marysugar.dagger_hilt_mvvm.network.BlogMapper
import com.marysugar.dagger_hilt_mvvm.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogApi: BlogApi,
        blogMapper: BlogMapper
    ): MainRepository {
        return MainRepository(blogApi, blogMapper)
    }
}