package com.example.thindie.core.network.di

import com.example.thindie.core.network.ConnectionListener
import com.example.thindie.core.network.ConnectionObserveAble
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ConnectionObserveAbleModule {
    @Binds
    fun bindConnectionObserveAble(listener: ConnectionListener): ConnectionObserveAble
}