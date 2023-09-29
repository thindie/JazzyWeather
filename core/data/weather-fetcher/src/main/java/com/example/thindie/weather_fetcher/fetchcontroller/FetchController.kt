package com.example.thindie.weather_fetcher.fetchcontroller

import com.example.thindie.domain.RatificationAble
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Named("fetchController")
internal class FetchController @Inject constructor(
    @Named("networkController") private val networkController: RatificationAble,
    @Named("timeController") private val timeController: RatificationAble,
) : RatificationAble {

    override fun isAllowed(): Boolean {
        return if (networkController.isAllowed())
            timeController.isAllowed()
        else false
    }

}