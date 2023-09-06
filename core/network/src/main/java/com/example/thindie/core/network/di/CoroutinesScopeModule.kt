package com.example.thindie.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob





@Module
@InstallIn(SingletonComponent::class)
class CoroutinesScopeModule {

    @Singleton
    @Provides
    fun provideCoroutineScope(@DispatchersIOModule.IODispatcher IO: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(
            SupervisorJob() + IO
        )
}
