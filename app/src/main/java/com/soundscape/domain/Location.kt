package com.soundscape.domain
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.soundscape.R
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
    val website : String get() = this.tags["contact:website"] ?: "NoWebsite"
    val musicalStyles : List<String> = listOf("#Metal", "#DrumAndBass", "#Psytrance", "#Schlager", "#Pop", "#IndieRock")
    val events : List<String> = listOf("Metal Night", "DrumAndBass Night", "Goa Night", "Oldies Night")
    val description : String = "asfdjkl asdfjsalkdj alskdfjsalkfj alskdfj kdfj asdfsadf kasdfj dkjd d ff asdf"

    fun getClosingTime() : String  {
        return "4 am"
    }
}
