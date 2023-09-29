package com.example.thindie.core.network

import android.net.ConnectivityManager
import android.net.Network
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Singleton
internal class ConnectionListener @Inject constructor(private val manager: ConnectivityManager) :
    ConnectionObserveAble {

    override fun isStateAvailAble(): Flow<ConnectionAllowAble> {
        return callbackFlow {

            val callBack = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(listened(true)) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(listened(false)) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(listened(false)) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(listened(false)) }
                }
            }

            manager.registerDefaultNetworkCallback(callBack)
            awaitClose {
                manager.unregisterNetworkCallback(callBack)
            }
        }.distinctUntilChanged()
    }

    private fun listened(case: Boolean): CallBackTransmitter {
        return CallBackTransmitter(case)
    }

}
