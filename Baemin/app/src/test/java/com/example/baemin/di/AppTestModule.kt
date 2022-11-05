package com.example.baemin.di

import com.example.baemin.data.TestOrderRepository
import com.example.baemin.data.TestRestaurantFoodRepository
import com.example.baemin.data.TestRestaurantRepository
import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.data.repository.order.OrderRepository
import com.example.baemin.data.repository.restaurant.RestaurantRepository
import com.example.baemin.data.repository.restaurant.food.RestaurantFoodRepository
import com.example.baemin.screen.main.home.restaurant.RestaurantCategory
import com.example.baemin.screen.main.home.restaurant.RestaurantListViewModel
import com.example.baemin.screen.order.OrderMenuListViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get()) }

    viewModel { (firebaseAuth: FirebaseAuth) -> OrderMenuListViewModel(get(), get(), firebaseAuth) }

    single<RestaurantRepository> { TestRestaurantRepository() }

    single<RestaurantFoodRepository> { TestRestaurantFoodRepository() }

    single<OrderRepository> { TestOrderRepository() }
}