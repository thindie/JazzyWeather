package com.example.thindie.core.network

import kotlinx.coroutines.flow.Flow

interface ConnectionObserveAble {
    fun isStateAvailAble(): Flow<ConnectionAllowAble>
}