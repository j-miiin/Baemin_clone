package com.example.baemin.screen.main.home.restaurant.detail.review

import androidx.core.os.bundleOf
import com.example.baemin.data.entity.RestaurantFoodEntity
import com.example.baemin.databinding.FragmentListBinding
import com.example.baemin.screen.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantReviewListFragment: BaseFragment<RestaurantReviewListViewModel, FragmentListBinding>() {

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    override val viewModel by viewModel<RestaurantReviewListViewModel>()

    override fun observeData() {

    }

    companion object {

        const val RESTAURANT_ID_KEY = "restaurantId"

        fun newInstance(restaurantId: Long, foodList: ArrayList<RestaurantFoodEntity>): RestaurantReviewListFragment {
            val bundle = bundleOf(
                RESTAURANT_ID_KEY to restaurantId,
            )
            return RestaurantReviewListFragment().apply {
                arguments = bundle
            }
        }
    }
}