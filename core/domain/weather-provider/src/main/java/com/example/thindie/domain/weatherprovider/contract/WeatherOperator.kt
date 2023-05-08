package com.example.thindie.domain.weatherprovider.contract

import com.example.thindie.domain.weatherprovider.repository.WeatherFetcherRepository

interface WeatherOperator : WeatherFetcherRepository

interface WeatherFetchRequirements {
    val location: String
    val latitude: Float
    val longitude: Float
}