package com.soundscape.infrastructure.LocationData

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(private val locationDao: LocationDao){
    fun getAllLocations() = locationDao.getAllLocations()
    fun insert(location: LocationData) = locationDao.insertLocation(location)
    fun insertAll(locations: List<LocationData>) = locationDao.insertAll(locations)

    companion object {
        @Volatile private var instance: LocationRepository? = null

        fun getInstance(locationDao: LocationDao) =
            instance ?: synchronized(this) {
                instance ?: LocationRepository(locationDao).also { instance = it }
            }
    }
}