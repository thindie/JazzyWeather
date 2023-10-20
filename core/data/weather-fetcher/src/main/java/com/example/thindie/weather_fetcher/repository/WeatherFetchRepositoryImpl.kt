package com.example.thindie.weather_fetcher.repository

import com.example.thindie.database.room.WeatherHourlyDao
import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.weather_fetcher.FetchPermission
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class WeatherFetchRepositoryImpl @Inject constructor(private val dao: WeatherHourlyDao) :
    RatificationObserver() {
    override fun observeRatification(): Flow<RatificationAble> {
        return dao.isEmpty().map {
            FetchPermission(it)
        }
    }
}