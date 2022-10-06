package com.example.baemin.screen.main.home.restaurant

import androidx.annotation.StringRes
import com.example.baemin.R

enum class RestaurantCategory(
    @StringRes val categoryNameId: Int,
    @StringRes val categoryTypeId: Int
) {

    ALL(R.string.all, R.string.all_type)

}