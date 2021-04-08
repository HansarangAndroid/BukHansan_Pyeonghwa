package com.example.lecturesopt28th.di

import com.example.lecturesopt28th.home.data.source.SearchUserDataSource
import com.example.lecturesopt28th.home.data.repository.SearchUserRepository
import com.example.lecturesopt28th.home.data.repository.SearchUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchUserRepository(searchUserDataSource: SearchUserDataSource): SearchUserRepository =
        SearchUserRepositoryImpl(searchUserDataSource)
}