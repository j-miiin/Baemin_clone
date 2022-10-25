package com.example.baemin.data.repository.restaurant.food

import com.example.baemin.data.entity.RestaurantFoodEntity

interface RestaurantFoodRepository {

    suspend fun getFoods(restaurantId: Long): List<RestaurantFoodEntity>

}