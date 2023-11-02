package com.example.thindie.weather_fetcher.fetchcontroller

import com.example.thindie.core.network.ConnectionObserveAble
import com.example.thindie.core.network.di.DispatchersIOModule
import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.weather_fetcher.FetchPermission
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@Singleton
@Named("networkController")
class NetWorkController @Inject constructor(
    private val observeAble: ConnectionObserveAble,
    @DispatchersIOModule.IODispatcher private val dispatchersIO: CoroutineDispatcher,
) : RatificationObserver() {


    override fun observeRatification(): Flow<RatificationAble> {
        return observeAble
            .isStateAvailAble()
            .map {
                FetchPermission(allowed = it.allow())
            }.flowOn(dispatchersIO)
    }
}