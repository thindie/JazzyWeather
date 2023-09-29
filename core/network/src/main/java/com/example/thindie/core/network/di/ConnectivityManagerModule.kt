package com.example.thindie.core.network.di

import android.app.Application
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class ConnectivityManagerModule {
    @Provides
    fun provideConnectivityManager(application: Application): ConnectivityManager {
        return application.getSystemService(ConnectivityManager::class.java)
    }
}