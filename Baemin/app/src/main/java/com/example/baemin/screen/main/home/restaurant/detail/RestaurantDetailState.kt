package com.example.baemin.screen.main.home.restaurant.detail

import com.example.baemin.data.entity.RestaurantEntity

sealed class RestaurantDetailState {

    object Uninitialized: RestaurantDetailState()

    object Loading: RestaurantDetailState()

    data class Success(
        val restaurantEntity: RestaurantEntity,
        val isLiked: Boolean? = null
    ): RestaurantDetailState()
}