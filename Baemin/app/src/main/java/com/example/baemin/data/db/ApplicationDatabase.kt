package com.example.baemin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baemin.data.db.dao.FoodMenuBasketDao
import com.example.baemin.data.db.dao.LocationDao
import com.example.baemin.data.db.dao.RestaurantDao
import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.data.entity.RestaurantEntity
import com.example.baemin.data.entity.RestaurantFoodEntity

@Database(
    entities = [LocationLatLngEntity::class, RestaurantEntity::class, RestaurantFoodEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase: RoomDatabase() {

    companion object {
        const val DB_NAME = "ApplicationDataBase.db"
    }

    abstract fun LocationDao(): LocationDao

    abstract fun RestaurantDao(): RestaurantDao

    abstract fun FoodMenuBasketDao(): FoodMenuBasketDao
}