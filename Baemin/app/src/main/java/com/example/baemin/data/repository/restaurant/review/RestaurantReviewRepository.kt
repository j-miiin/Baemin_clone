package com.example.baemin.data.repository.restaurant.review

import com.example.baemin.data.entity.RestaurantReviewEntity

interface RestaurantReviewRepository {

    suspend fun getReviews(restaurantTitle: String): List<RestaurantReviewEntity>
}