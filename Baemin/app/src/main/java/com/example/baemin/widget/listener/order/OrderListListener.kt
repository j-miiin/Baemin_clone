package com.example.baemin.widget.listener.order

import com.example.baemin.widget.listener.AdapterListener

interface OrderListListener: AdapterListener {

    fun writeRestaurantReview(orderId: String, restaurantTitle: String)
}