package com.edmundo.domain.datasource

import com.edmundo.domain.model.CheckInBody
import com.edmundo.domain.model.Event
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsListService {

    @GET("events")
    suspend fun getEvents(): Response<List<Event>>

    @GET("events/{eventId}")
    suspend fun getEventDetails(
        @Path("eventId") eventId: String
    ): Response<Event>

    @POST("checkin")
    suspend fun checkIn(
        @Body body: CheckInBody
    ): Response<Void>

}