package com.example.thindie.core.network

internal data class CallBackTransmitter(
    val isAllowed: Boolean,
) : ConnectionAllowAble {
    override fun allow(): Boolean {
        return isAllowed
    }
}