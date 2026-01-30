package com.mudassir.taskmanagerapp.di

import com.mudassir.taskmanagerapp.data.api.ApiService
import com.mudassir.taskmanagerapp.data.api.FakeApiService
import com.mudassir.taskmanagerapp.data.repository.TaskRepository
import com.mudassir.taskmanagerapp.data.repository.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return FakeApiService()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        apiService: ApiService
    ): TaskRepository {
        return TaskRepositoryImpl(apiService)
    }
}