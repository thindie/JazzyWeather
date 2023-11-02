package com.example.thindie.weather_fetcher.mappers

import com.example.thindie.domain.EventClass
import com.example.thindie.events_resources.R
import com.example.thindie.weather_fetcher.EventKind


fun EventClass<EventKind>.toRes(): EventClass<Int> {

    return when (this.getEvent()) {
        EventKind.NET -> {
            ResEvent(R.string.on_exception_not_connected)
        }
        EventKind.EMPTY -> ResEvent(R.string.on_information_is_empty)
        EventKind.MAPPING -> ResEvent(R.string.on_exception_params_not_valid)
        EventKind.PARSING -> ResEvent(R.string.on_exception_unexpected)
        EventKind.STUB -> ResEvent(R.string.on_exception_params_not_valid)
    }
}


data class KindEvent(private val eventKind: EventKind) : EventClass<EventKind> {
    override fun getEvent(): EventKind {
        return eventKind
    }
}

data class ResEvent(private val res: Int) : EventClass<Int> {
    override fun getEvent(): Int {
        return res
    }

}