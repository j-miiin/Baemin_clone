package com.example.baemin.widget.adapter.viewholder.retaurant

import com.example.baemin.model.restaurant.RestaurantModel
import com.example.baemin.widget.listener.restaurant.RestaurantListListener

interface RestaurantLikeListListener: RestaurantListListener {

    fun onDislikeItem(model: RestaurantModel)
}