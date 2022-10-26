package com.example.baemin.screen.main.home.restaurant.detail.review

import com.example.baemin.model.restaurant.review.RestaurantReviewModel

sealed class RestaurantReviewState {

    object Uninitialized: RestaurantReviewState()

    object Loading: RestaurantReviewState()

    data class Success(
        val reviewList: List<RestaurantReviewModel>
    ): RestaurantReviewState()
}
