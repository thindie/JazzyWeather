package com.example.thindie.domain

interface EventTransmitter<T> {
    fun send(eventClass: EventClass<T>)

}
