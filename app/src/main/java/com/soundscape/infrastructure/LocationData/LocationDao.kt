package com.soundscape.infrastructure.LocationData

import androidx.lifecycle.LiveData
import androidx.room.*
import com.soundscape.infrastructure.LocationData.LocationData as LocationData
import java.util.concurrent.Flow


@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //ignore location if id is already in database
    fun insertLocation(location: LocationData)

    @Update
    suspend fun updateLocation(location: LocationData)

    @Delete
    suspend fun deleteLocation(location: LocationData) //maybe we won't need this?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(locations: List<LocationData>)

    @Query("SELECT * FROM location")
    fun getAllLocations(): LiveData<List<LocationData>>

    //get location like name
    @Query("SELECT * FROM location WHERE name LIKE :name")
    fun getLocationByName(name: String): LocationData


}