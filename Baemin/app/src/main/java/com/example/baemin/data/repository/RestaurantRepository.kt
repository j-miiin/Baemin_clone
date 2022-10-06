package com.example.baemin.data.repository

import com.example.baemin.data.entity.RestaurantEntity
import com.example.baemin.screen.main.home.restaurant.RestaurantCategory

interface RestaurantRepository {

    suspend fun getList(
        restaurantCategory: RestaurantCategory
    ): List<RestaurantEntity>
}