package com.example.baemin.data.response.restaurant

import com.example.baemin.data.entity.RestaurantFoodEntity

data class RestaurantFoodResponse (
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val restaurantId: Long,
    val restaurantTitle: String
) {

    fun toEntity(restaurantId: Long, restaurantTitle: String) = RestaurantFoodEntity(
        id,
        title,
        description,
        price.toDouble().toInt(),
        imageUrl,
        restaurantId,
        restaurantTitle
    )
}