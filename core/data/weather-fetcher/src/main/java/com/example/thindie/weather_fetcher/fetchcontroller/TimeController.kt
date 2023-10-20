package com.example.thindie.weather_fetcher.fetchcontroller

import com.example.thindie.domain.RatificationAble
import javax.inject.Inject
import javax.inject.Named


@Named("timeController")
internal class TimeController @Inject constructor() : RatificationAble {

    private val halfHour = 10000L
    private var fetchedAt = 0L
    private var isAllowed = true
    override fun isAllowed(): Boolean {
        if (isAllowed) {
            fetchedAt = System.currentTimeMillis()
            isAllowed = false
        } else {
            if (System.currentTimeMillis() - fetchedAt > halfHour) {
                isAllowed = true
            }
        }
        return isAllowed
    }
}
