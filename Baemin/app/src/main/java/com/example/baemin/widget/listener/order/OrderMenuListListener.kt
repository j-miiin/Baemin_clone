package com.example.baemin.widget.listener.order

import com.example.baemin.model.restaurant.food.FoodModel
import com.example.baemin.widget.listener.AdapterListener

interface OrderMenuListListener: AdapterListener {

    fun onRemoveItem(model: FoodModel)
}