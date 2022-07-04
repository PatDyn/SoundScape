package com.soundscape.infrastructure.LocationData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.soundscape.infrastructure.LocationData.SeedDatabaseWorker.Companion.KEY_FILENAME


@Database(entities = [LocationData::class], version = 1, exportSchema = false)
abstract class LocationDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationDao

    companion object {

        @Volatile private var instance: LocationDatabase? = null

        fun getInstance(context: Context): LocationDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): LocationDatabase {
            return Room.databaseBuilder(context, LocationDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries() // allow main thread queries to DB, just for testing
                .fallbackToDestructiveMigration()
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to LOCATION_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)

                        }
                    }
                )
                .build()
        }
    }
}
val testLocation = LocationData(
    id = 1,
    name = "Test Location",
    type = "Test Type",
    lat = 1.3,
    lon = 1.2,
    tags = "Test Tags",
    geohash = "Test GeoHash",
    favorite = false
)