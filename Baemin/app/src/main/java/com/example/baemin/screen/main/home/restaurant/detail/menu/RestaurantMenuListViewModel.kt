package com.example.baemin.screen.main.home.restaurant.detail.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.baemin.data.entity.RestaurantFoodEntity
import com.example.baemin.data.repository.restaurant.food.RestaurantFoodRepository
import com.example.baemin.model.restaurant.food.FoodModel
import com.example.baemin.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantMenuListViewModel(
    private val restaurantId: Long,
    private val foodEntityList: List<RestaurantFoodEntity>,
    private val restaurantFoodRepository: RestaurantFoodRepository
): BaseViewModel() {

    val restaurantFoodListLiveData = MutableLiveData<List<FoodModel>>()

    val menuBasketLiveData = MutableLiveData<RestaurantFoodEntity>()

    val isClearNeedInBasketLiveData = MutableLiveData<Pair<Boolean, () -> Unit>>()

    override fun fetchData(): Job = viewModelScope.launch {
        restaurantFoodListLiveData.value = foodEntityList.map {
            FoodModel(
                id = it.hashCode().toLong(),
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                restaurantId = it.restaurantId,
                foodId = it.id,
                restaurantTitle = it.restaurantTitle
            )
        }
    }

    fun insertMenuInBasket(foodModel: FoodModel) = viewModelScope.launch {
        val restaurantMenuListInBasket = restaurantFoodRepository.getFoodMenuListInBasket(restaurantId)
        val foodMenuEntity = foodModel.toEntity(restaurantMenuListInBasket.size)
        val anotherRestaurantMenuListInBasket =
            restaurantFoodRepository.getAllFoodMenuListInBasket().filter { it.restaurantId != restaurantId }
        if (anotherRestaurantMenuListInBasket.isNotEmpty()) {
            isClearNeedInBasketLiveData.value = Pair(true, { clearMenuAndInsertNewMenuInBasket(foodMenuEntity) })
        } else {
            restaurantFoodRepository.insertFoodMenuInBasket(foodMenuEntity)
            menuBasketLiveData.value = foodMenuEntity
        }
    }

    private fun clearMenuAndInsertNewMenuInBasket(foodMenuEntity: RestaurantFoodEntity) = viewModelScope.launch {
        restaurantFoodRepository.clearFoodMenuListInBasket()
        restaurantFoodRepository.insertFoodMenuInBasket(foodMenuEntity)
        menuBasketLiveData.value = foodMenuEntity
    }
}