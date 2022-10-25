package com.example.baemin.data.response.restaurant

import com.example.baemin.data.entity.RestaurantFoodEntity

data class RestaurantFoodResponse (
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val restaurantId: Long
) {

    fun toEntity(restaurantId: Long) = RestaurantFoodEntity(
        id,
        title,
        description,
        price.toDouble().toInt(),
        imageUrl,
        restaurantId
    )
}