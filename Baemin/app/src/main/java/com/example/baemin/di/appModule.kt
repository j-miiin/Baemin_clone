package com.example.baemin.di

import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.data.entity.MapSearchInfoEntity
import com.example.baemin.data.entity.RestaurantEntity
import com.example.baemin.data.entity.RestaurantFoodEntity
import com.example.baemin.data.repository.map.DefaultMapRepository
import com.example.baemin.data.repository.map.MapRepository
import com.example.baemin.data.repository.restaurant.DefaultRestaurantRepository
import com.example.baemin.data.repository.restaurant.RestaurantRepository
import com.example.baemin.data.repository.restaurant.food.DefaultRestaurantFoodRepository
import com.example.baemin.data.repository.restaurant.food.RestaurantFoodRepository
import com.example.baemin.data.repository.restaurant.review.DefaultRestaurantReviewRepository
import com.example.baemin.data.repository.restaurant.review.RestaurantReviewRepository
import com.example.baemin.data.repository.user.DefaultUserRepository
import com.example.baemin.data.repository.user.UserRepository
import com.example.baemin.screen.main.home.HomeViewModel
import com.example.baemin.screen.main.home.restaurant.RestaurantCategory
import com.example.baemin.screen.main.home.restaurant.RestaurantListViewModel
import com.example.baemin.screen.main.home.restaurant.detail.RestaurantDetailViewModel
import com.example.baemin.screen.main.home.restaurant.detail.menu.RestaurantMenuListViewModel
import com.example.baemin.screen.main.home.restaurant.detail.review.RestaurantReviewListViewModel
import com.example.baemin.screen.main.my.MyViewModel
import com.example.baemin.screen.mylocation.MyLocationViewModel
import com.example.baemin.util.provider.DefaultResourcesProvider
import com.example.baemin.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val appModule = module {

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { MyViewModel() }
    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get()) }
    viewModel { (mapSearchInfoEntity: MapSearchInfoEntity) -> MyLocationViewModel(mapSearchInfoEntity, get(), get()) }
    viewModel { (restaurantEntity: RestaurantEntity) -> RestaurantDetailViewModel(restaurantEntity, get(), get()) }
    viewModel { (restaurantId: Long, restaurantFoodList: List<RestaurantFoodEntity>) ->
        RestaurantMenuListViewModel(restaurantId, restaurantFoodList, get())
    }
    viewModel { (restaurantTitle: String) -> RestaurantReviewListViewModel(restaurantTitle, get()) }

    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }
    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get(), get()) }
    single<RestaurantFoodRepository> { DefaultRestaurantFoodRepository(get(), get(), get()) }
    single<RestaurantReviewRepository> { DefaultRestaurantReviewRepository(get()) }

    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }

    single(named("map")) { provideMapRetrofit(get(), get()) }
    single(named("food")) { provideFoodRetrofit(get(), get()) }

    single { provideMapApiService(get(qualifier = named("map"))) }
    single { provideFoodApiService(get(qualifier = named("food"))) }

    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }
    single { provideRestaurantDao(get()) }
    single { provideFoodMenuBasketDao(get()) }

    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }

    single { Dispatchers.IO }
    single { Dispatchers.Main }
}