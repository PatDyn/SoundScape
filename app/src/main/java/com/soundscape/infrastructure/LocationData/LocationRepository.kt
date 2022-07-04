package com.soundscape.infrastructure.LocationData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(private val locationDao: LocationDao){
    val allLocations: LiveData<List<LocationData>> = locationDao.getAllLocations()
    //val searchResults = MutableLiveData<List<LocationData>>()

    suspend fun insertLocation(location: LocationData) = locationDao.insertLocation(location)
    suspend fun insertAllLocations(locations: List<LocationData>) = locationDao.insertAll(locations)

    companion object {
        @Volatile private var instance: LocationRepository? = null

        fun getInstance(locationDao: LocationDao) =
            instance ?: synchronized(this) {
                instance ?: LocationRepository(locationDao).also { instance = it }
            }
    }
}