package com.edmundo.domain.datasource

import android.provider.CalendarContract
import com.edmundo.domain.model.Event

interface EventsListContract {

   suspend fun getEvents(): List<Event>

    suspend fun getEventDetails(eventId: String): Event

    suspend fun checkIn(
        eventId: String,
        name: String,
        email: String
    ): Void

}