package com.soundscape.domain
import org.json.*

class Bar(
    val type: String,
    val id: Long,
    val name: String,
    //val openingHours: String?,
    val lat: Double,
    val lon: Double,
    val tags: JSONObject,
    val geohash: String)
