package com.example.baemin.widget.listener.restaurant

import com.example.baemin.model.restaurant.RestaurantModel
import com.example.baemin.widget.listener.AdapterListener

interface RestaurantListListener: AdapterListener {

    fun onClickItem(model: RestaurantModel)
}