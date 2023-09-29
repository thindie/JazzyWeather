package com.example.thindie.weather_fetcher.fetchcontroller

import com.example.thindie.domain.RatificationAble
import javax.inject.Inject
import javax.inject.Named

@Named("timeController")
internal class TimeController @Inject constructor() : RatificationAble {

    private val halfHour = 1_800_000L


    private var fromLastFetch: Long = 0L

    private fun isInTime() = System.currentTimeMillis() - fromLastFetch > halfHour
    override fun isAllowed(): Boolean {
        return if (isInTime()) {
            fromLastFetch = System.currentTimeMillis()
            true
        } else false
    }
}