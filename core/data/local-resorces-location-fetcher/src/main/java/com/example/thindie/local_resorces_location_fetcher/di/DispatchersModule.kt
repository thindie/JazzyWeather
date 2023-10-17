package com.example.thindie.local_resorces_location_fetcher.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DispatchersIOModule {

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
class DispatchersMainModule {

    @MainDispatcher
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class MainDispatcher
}

