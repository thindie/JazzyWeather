package com.example.thindie.date_time_management.di

import com.example.thindie.date_time_management.repository.TimeRepositoryImpl
import com.example.thindie.domain.TimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TimeRepositoryModule {
    @Binds
    fun bindTimeRepository(impl: TimeRepositoryImpl): TimeRepository
}