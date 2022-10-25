package com.example.baemin.model.restaurant.food

import com.example.baemin.model.CellType
import com.example.baemin.model.Model


data class FoodModel(
    override val id: Long,
    override val type: CellType = CellType.FOOD_CELL,
    val title: String,
    val description: String,
    val price: Int,
    val imageUrl: String,
    val restaurantId: Long,
): Model(id, type) {

}
