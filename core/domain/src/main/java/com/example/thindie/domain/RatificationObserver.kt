package com.example.thindie.domain

import kotlinx.coroutines.flow.Flow

abstract class RatificationObserver {
    abstract fun observeRatification(): Flow<RatificationAble>
}