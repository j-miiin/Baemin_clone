package com.example.baemin.di

import com.example.baemin.data.repository.map.DefaultMapRepository
import com.example.baemin.data.repository.map.MapRepository
import com.example.baemin.data.repository.restaurant.DefaultRestaurantRepository
import com.example.baemin.data.repository.restaurant.RestaurantRepository
import com.example.baemin.screen.main.home.HomeViewModel
import com.example.baemin.screen.main.home.restaurant.RestaurantCategory
import com.example.baemin.screen.main.home.restaurant.RestaurantListViewModel
import com.example.baemin.screen.main.my.MyViewModel
import com.example.baemin.util.provider.DefaultResourcesProvider
import com.example.baemin.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { MyViewModel() }
    viewModel { (restaurantCategory: RestaurantCategory) -> RestaurantListViewModel(restaurantCategory, get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get()) }
    single<MapRepository> { DefaultMapRepository(get(), get()) }

    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }

    single { provideMapRetrofit(get(), get()) }

    single { provideMapApiService(get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }
}