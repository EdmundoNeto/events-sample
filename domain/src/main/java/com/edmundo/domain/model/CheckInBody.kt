package com.edmundo.domain.model

import com.google.gson.annotations.SerializedName

data class CheckInBody(
    @SerializedName("eventId") val eventId: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)