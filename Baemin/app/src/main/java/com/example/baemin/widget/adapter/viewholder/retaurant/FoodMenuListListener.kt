package com.example.baemin.widget.adapter.viewholder.retaurant

import com.example.baemin.model.restaurant.food.FoodModel
import com.example.baemin.widget.listener.AdapterListener

interface FoodMenuListListener: AdapterListener {

    fun onClickItem(model: FoodModel)
}