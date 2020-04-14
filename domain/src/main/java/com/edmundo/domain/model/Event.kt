package com.edmundo.domain.model

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

data class Event(
    @SerializedName("people") val people: List<Person>,
    @SerializedName("date") val date: Long,
    @SerializedName("description") val description: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("price") val price: Double,
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: String,
    @SerializedName("cupons") val cupons: List<Coupon>
) {
    fun toFormattedPrice(price: Double): String {

        val format = DecimalFormat.getInstance(Locale("pt", "BR"))

        format.minimumFractionDigits = 2
        format.maximumFractionDigits = 2

        return "R$ " + format.format(price)
    }

}