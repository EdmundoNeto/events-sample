package com.edmundo.domain.repository

import com.edmundo.domain.SafeRequest
import com.edmundo.domain.datasource.EventsListContract
import com.edmundo.domain.datasource.EventsListService
import com.edmundo.domain.model.CheckInBody
import com.edmundo.domain.model.Event

class EventListRepository(private val service: EventsListService) :
    EventsListContract,
    SafeRequest() {

    override suspend fun getEvents(): List<Event> = apiRequest {
        service.getEvents()
    }

    override suspend fun getEventDetails(eventId: String) = apiRequest {
        service.getEventDetails(eventId)
    }

    override suspend fun checkIn(eventId: String, name: String, email: String) = apiRequest {
        service.checkIn(CheckInBody(eventId, name, email))
    }
}