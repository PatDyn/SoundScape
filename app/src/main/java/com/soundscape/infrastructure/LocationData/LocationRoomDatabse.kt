package com.soundscape.infrastructure.LocationData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [LocationData::class], version = 1, exportSchema = false)
//exportSchema false : dont save a history
//version = 1 : table version, has to be increased with every update
abstract class LocationRoomDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao //you can have multiple Dao's in here

    companion object {
        @Volatile
        private var INSTANCE: LocationRoomDatabase? = null

        fun getDatabase(context: Context): LocationRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocationRoomDatabase::class.java,
                    "location_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}