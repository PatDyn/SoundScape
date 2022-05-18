package com.soundscape.domain
import kotlinx.serialization.Serializable

@Serializable
class Location(
    val type: String,
    val id: Long,
    val lat: Double,
    val lon: Double,
    val tags: HashMap<String, String>,
    val geohash: String
) {
    val name get() = this.tags["name"] ?: "NoName"
    val locationType : String get() = this.tags["amenity"] ?: "NoLocationType"
    val distance = "999km"
    val isOpen = false
    //val openingHours get() = this.tags["opening_hours"] ?: "NoOpeningHours"
    val genres = listOf("Metal", "Drum and Bass", "Psytrance")
    val matchScore = "99%"
    fun getClosingTime() : String  {
        return "4 am"
    }
}
