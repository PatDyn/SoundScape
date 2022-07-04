package com.soundscape.infrastructure.LocationData

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseViewmodel(
    application: Application
) : ViewModel() {

    val allLocations: LiveData<List<LocationData>>
    private val repository: LocationRepository

    init {
        val locationDao = LocationDatabase.getInstance(application).locationDao()
        repository = LocationRepository(locationDao)

        allLocations = repository.allLocations
    }

    fun insertLocation(locationData: LocationData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLocation(locationData)
        }
    }
    fun insertAllLocations(locationData: List<LocationData>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllLocations(locationData)
        }
    }

}