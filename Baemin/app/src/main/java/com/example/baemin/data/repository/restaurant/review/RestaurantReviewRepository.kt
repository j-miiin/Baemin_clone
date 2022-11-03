package com.example.baemin.data.repository.restaurant.review

interface RestaurantReviewRepository {

    suspend fun getReviews(restaurantTitle: String): DefaultRestaurantReviewRepository.Result
}