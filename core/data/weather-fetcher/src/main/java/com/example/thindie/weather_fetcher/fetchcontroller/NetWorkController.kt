package com.example.thindie.weather_fetcher.fetchcontroller

import com.example.thindie.core.network.ConnectionObserveAble
import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.domain.RatificationAble
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Singleton
@Named("networkController")
class NetWorkController @Inject constructor(
    private val observeAble: ConnectionObserveAble,
    @DispatchersIOModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
    private val scope: CoroutineScope,
) :
    RatificationAble {


    private var isAllowed: () -> Boolean = { false }

    init {
        listenConnection()
    }

    override fun isAllowed(): Boolean {
        return isAllowed.invoke()
    }

    private fun listenConnection() {
        observeAble
            .isStateAvailAble()
            .onEach { callBack ->
                isAllowed = { callBack.allow() }
            }
            .launchIn(scope)
    }
}