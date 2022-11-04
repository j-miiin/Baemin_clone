package com.example.baemin.di

import com.example.baemin.data.TestRestaurantRepository
import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.data.repository.restaurant.RestaurantRepository
import com.example.baemin.screen.main.home.restaurant.RestaurantCategory
import com.example.baemin.screen.main.home.restaurant.RestaurantListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get()) }

    single<RestaurantRepository> { TestRestaurantRepository() }
}