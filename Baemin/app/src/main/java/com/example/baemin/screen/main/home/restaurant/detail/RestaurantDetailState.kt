package com.example.baemin.screen.main.home.restaurant.detail

import com.example.baemin.data.entity.RestaurantEntity
import com.example.baemin.data.entity.RestaurantFoodEntity

sealed class RestaurantDetailState {

    object Uninitialized: RestaurantDetailState()

    object Loading: RestaurantDetailState()

    data class Success(
        val restaurantEntity: RestaurantEntity,
        val restaurantFoodList: List<RestaurantFoodEntity>? = null,
        val isLiked: Boolean? = null
    ): RestaurantDetailState()
}