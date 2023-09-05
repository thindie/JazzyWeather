package com.example.thindie.core.network.di

import com.example.thindie.core.network.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.open-meteo.com"

    @Provides
    internal fun provideOkHttpClient() = OkHttpClient()
        .newBuilder()
        .addInterceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            chain.proceed(request)
        }
        .build()

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    internal fun provideApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}