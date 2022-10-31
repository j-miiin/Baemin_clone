package com.example.baemin.model.order

import com.example.baemin.data.entity.OrderEntity
import com.example.baemin.data.entity.RestaurantFoodEntity
import com.example.baemin.model.CellType
import com.example.baemin.model.Model


data class OrderModel(
    override val id: Long,
    override val type: CellType = CellType.ORDER_CELL,
    val orderId: String,
    val userId: String,
    val restaurantId: Long,
    val foodMenuList: List<RestaurantFoodEntity>
): Model(id, type) {

    fun toEntity() = OrderEntity(
        id = orderId,
        userId = userId,
        restaurantId = restaurantId,
        foodMenuList = foodMenuList
    )
}