package com.example.thindie.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

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
