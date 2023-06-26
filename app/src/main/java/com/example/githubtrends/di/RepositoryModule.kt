package com.example.githubtrends.di


import com.example.githubtrends.data.utils.ItemMapper
import com.example.githubtrends.data.network.api.ApiService
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.data.repositoryimpl.RepositoryImpl
import com.example.githubtrends.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency injection class that provides instances related to repositories
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideResponseConverter(): ResponseConverter {
        return ResponseConverter()
    }

    @Provides
    @Singleton
    fun provideItemMapper() = ItemMapper()


    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService,
        responseConverter: ResponseConverter,
        basketMapper: ItemMapper
    ): Repository {
        return RepositoryImpl(
            apiService,
            responseConverter,
            basketMapper
        )
    }
}