package com.example.thindie.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatchersModule {

    @IODispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IODispatcher
}

@Module
@InstallIn(SingletonComponent::class)
class CoroutinesScopeModule {

    @Singleton
    @Provides
    fun provideCoroutineScope(@DispatchersModule.IODispatcher IO: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(
            SupervisorJob() + IO
        )
}
