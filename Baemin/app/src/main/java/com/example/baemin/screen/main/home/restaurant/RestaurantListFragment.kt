package com.example.baemin.screen.main.home.restaurant

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import com.example.baemin.data.entity.LocationLatLngEntity
import com.example.baemin.databinding.FragmentRestaurantListBinding
import com.example.baemin.model.restaurant.RestaurantModel
import com.example.baemin.screen.base.BaseFragment
import com.example.baemin.util.provider.ResourcesProvider
import com.example.baemin.widget.adapter.ModelRecyclerAdapter
import com.example.baemin.widget.listener.restaurant.RestaurantListListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class RestaurantListFragment: BaseFragment<RestaurantListViewModel, FragmentRestaurantListBinding>() {

    private val restaurantCategory by lazy { arguments?.getSerializable(RESTAURANT_CATEGORY_KEY) as RestaurantCategory }

    private val locationLatLng by lazy { arguments?.getParcelable<LocationLatLngEntity>(LOCATION_KEY) }

    override val viewModel by viewModel<RestaurantListViewModel> {
        parametersOf(
            restaurantCategory,
            locationLatLng
        ) }

    override fun getViewBinding(): FragmentRestaurantListBinding = FragmentRestaurantListBinding.inflate(layoutInflater)

    private val resourcesProvider by inject<ResourcesProvider>()

    private val adapter by lazy {
        ModelRecyclerAdapter<RestaurantModel, RestaurantListViewModel>(
            listOf(),
            viewModel,
            resourcesProvider,
            adapterListener = object : RestaurantListListener {
                override fun onClickItem(model: RestaurantModel) {
                    Toast.makeText(requireContext(), "$model", Toast.LENGTH_SHORT).show()
                }

            })
    }

    override fun initViews() = with(binding) {
        recyclerView.adapter = adapter
    }

    override fun observeData() {
        viewModel.restaurantListLiveData.observe(viewLifecycleOwner) {
            Log.d("restaurantList", it.toString())
            adapter.submitList(it)
        }
    }

    companion object {
        const val RESTAURANT_CATEGORY_KEY = "restaurantCategory"
        const val LOCATION_KEY = "location"

        fun newInstance(restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity): RestaurantListFragment {
            return RestaurantListFragment().apply {
                arguments = bundleOf(
                    RESTAURANT_CATEGORY_KEY to restaurantCategory,
                    LOCATION_KEY to locationLatLng
                )
            }
        }
    }

}