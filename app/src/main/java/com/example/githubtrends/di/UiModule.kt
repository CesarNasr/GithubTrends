package com.example.githubtrends.di

import android.content.Context
import com.example.githubtrends.presentation.adapters.ReposAdapter
import com.example.githubtrends.presentation.utils.ResourcesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    @Singleton
    fun provideReposAdapter(): ReposAdapter {
        return ReposAdapter()
    }

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourcesProvider {
        return ResourcesProvider(context)
    }

}