package com.example.baemin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baemin.data.db.dao.LocationDao
import com.example.baemin.data.entity.LocationLatLngEntity

@Database(
    entities = [LocationLatLngEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun LocationDao(): LocationDao
}