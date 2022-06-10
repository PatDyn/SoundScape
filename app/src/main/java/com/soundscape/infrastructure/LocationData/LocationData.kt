package com.soundscape.infrastructure.LocationData

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "location")
data class LocationData (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "lon")
    val lon: Double,
    @ColumnInfo(name = "tags")
    val tags: String, //sorry ne Hashmap geht nicht, muss String mit maybe Kommas getrennt sein
    @ColumnInfo(name = "geohash")
    val geohash: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean = false
)