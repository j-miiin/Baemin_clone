package com.example.baemin.data.repository.restaurant.review

import com.example.baemin.data.entity.RestaurantReviewEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultRestaurantReviewRepository(
    private val ioDispatcher: CoroutineDispatcher
): RestaurantReviewRepository {

    override suspend fun getReviews(restaurantTitle: String): List<RestaurantReviewEntity> = withContext(ioDispatcher) {
        return@withContext (0..10).map {
            RestaurantReviewEntity(
                id = 0,
                title = "제목 $it",
                description = "내용 $it",
                grade = (1 until  5).random(),
            )
        }
    }
}