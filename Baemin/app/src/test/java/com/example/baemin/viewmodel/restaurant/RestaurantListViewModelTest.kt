package com.example.baemin.viewmodel.restaurant

import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.data.repository.restaurant.RestaurantRepository
import com.example.baemin.model.restaurant.RestaurantModel
import com.example.baemin.screen.main.home.restaurant.RestaurantCategory
import com.example.baemin.screen.main.home.restaurant.RestaurantListViewModel
import com.example.baemin.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

@ExperimentalCoroutinesApi
internal class RestaurantListViewModelTest: ViewModelTest() {

    // [RestaurantCategory]
    // [LocationLatLngEntity]

    private var restaurantCategory = RestaurantCategory.ALL

    private val locationLatLngEntity: LocationLatLngEntity = LocationLatLngEntity(0.0, 0.0)

    private val restaurantListViewModel by inject<RestaurantListViewModel> {
        parametersOf(
            restaurantCategory,
            locationLatLngEntity
        )
    }

    private val restaurantRepository by inject<RestaurantRepository>()

    @Test
    fun `test load restaurant list category All`() = runBlocking {
        val testObservable = restaurantListViewModel.restaurantListLiveData.test()

        restaurantListViewModel.fetchData()

        testObservable.assertValuesSequence(
            listOf(
                restaurantRepository.getList(restaurantCategory, locationLatLngEntity).map {
                    RestaurantModel(
                        id = it.id,
                        restaurantInfoId = it.restaurantInfoId,
                        restaurantCategory = it.restaurantCategory,
                        restaurantTitle = it.restaurantTitle,
                        restaurantImageUrl = it.restaurantImageUrl,
                        grade = it.grade,
                        reviewCount = it.reviewCount,
                        deliveryTimeRange = it.deliveryTimeRange,
                        deliveryTipRange = it.deliveryTipRange,
                        restaurantTelNumber = it.restaurantTelNumber
                    )
                }
            )
        )
    }
}