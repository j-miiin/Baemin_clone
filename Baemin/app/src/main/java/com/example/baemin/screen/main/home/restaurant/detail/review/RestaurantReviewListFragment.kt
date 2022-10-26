package com.example.baemin.screen.main.home.restaurant.detail.review

import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.baemin.databinding.FragmentListBinding
import com.example.baemin.model.restaurant.food.FoodModel
import com.example.baemin.model.restaurant.review.RestaurantReviewModel
import com.example.baemin.screen.base.BaseFragment
import com.example.baemin.util.provider.ResourcesProvider
import com.example.baemin.widget.adapter.ModelRecyclerAdapter
import com.example.baemin.widget.adapter.viewholder.retaurant.FoodMenuListListener
import com.example.baemin.widget.listener.AdapterListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantReviewListFragment: BaseFragment<RestaurantReviewListViewModel, FragmentListBinding>() {

    override fun getViewBinding(): FragmentListBinding = FragmentListBinding.inflate(layoutInflater)

    override val viewModel by viewModel<RestaurantReviewListViewModel> {
        parametersOf(
            arguments?.getString(RESTAURANT_TITLE_KEY)
        )
    }

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantReviewModel, RestaurantReviewListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : AdapterListener { }
        )
    }

    override fun initViews() {
        binding.recyclerView.adapter = adapter
    }

    override fun observeData() = viewModel.reviewStateLiveData.observe(viewLifecycleOwner) {
        when (it) {
            is RestaurantReviewState.Success -> {
                handleSuccess(it)
            }
            else -> {}
        }
    }

    private fun handleSuccess(state: RestaurantReviewState.Success) {
        adapter.submitList(state.reviewList)
    }

    companion object {

        const val RESTAURANT_TITLE_KEY = "restaurantId"

        fun newInstance(restaurantTitle: String): RestaurantReviewListFragment {
            val bundle = bundleOf(
                RESTAURANT_TITLE_KEY to restaurantTitle,
            )
            return RestaurantReviewListFragment().apply {
                arguments = bundle
            }
        }
    }
}