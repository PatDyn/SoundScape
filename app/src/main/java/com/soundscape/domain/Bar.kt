package com.soundscape.domain
import kotlinx.serialization.Serializable

@Serializable
class Bar(
    val type: String,
    val id: Long,
    //val openingHours: String?,
    val lat: Double,
    val lon: Double,
    val tags: HashMap<String, String>,
    val geohash: String
) {
    val name get() = this.tags["name"] ?: "NoName";
}
