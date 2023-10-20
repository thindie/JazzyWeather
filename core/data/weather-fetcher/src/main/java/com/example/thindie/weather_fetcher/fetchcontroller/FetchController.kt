package com.example.thindie.weather_fetcher.fetchcontroller

import com.example.thindie.domain.RatificationAble
import com.example.thindie.domain.RatificationObserver
import com.example.thindie.weather_fetcher.FetchPermission
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@Named("fetchController")
internal class FetchController @Inject constructor(
    @Named("networkController") private val networkController: RatificationObserver,
    @Named("timeController") private val timeController: RatificationAble,
) : RatificationObserver() {

    override fun observeRatification(): Flow<RatificationAble> {
        return networkController
            .observeRatification()
            .map { networkPermission ->
                if (timeController.isAllowed()) {
                    FetchPermission(allowed = networkPermission.isAllowed())
                } else {
                    FetchPermission(
                        allowed = false
                    )
                }
            }
    }

}