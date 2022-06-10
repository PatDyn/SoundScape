package com.soundscape.infrastructure.LocationData

import androidx.room.*
import com.soundscape.infrastructure.LocationData.LocationData as LocationData
import java.util.concurrent.Flow


@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //ignore location if id is already in database
    suspend fun insertLocation(location: LocationData)
    @Update
    suspend fun updateLocation(location: LocationData)
    @Delete
    suspend fun deleteLocation(location: LocationData) //maybe we won't need this?

}