package com.example.baemin.screen.main.home

import com.example.baemin.databinding.FragmentHomeBinding
import com.example.baemin.screen.base.BaseFragment
import com.example.baemin.screen.main.home.restaurant.RestaurantCategory
import com.example.baemin.screen.main.home.restaurant.RestaurantListFragment
import com.example.baemin.widget.adapter.RestaurantListFragmentPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private lateinit var viewPagerAdapter: RestaurantListFragmentPagerAdapter

    override fun initViews() {
        super.initViews()
        initViewPager()
    }

    private fun initViewPager() = with(binding) {
        val restaurantCategories = RestaurantCategory.values()

        if (::viewPagerAdapter.isInitialized.not()) {
            val restaurantListFragmentList = restaurantCategories.map {
                RestaurantListFragment.newInstance(it)
            }
            viewPagerAdapter = RestaurantListFragmentPagerAdapter(
                this@HomeFragment,
                restaurantListFragmentList
            )
            viewPager.adapter = viewPagerAdapter
        }

        // 페이지가 바뀔 때마다 fragment를 바꾸는 것이 아닌, 한 번 만들면 계속 사용하도록 함
        viewPager.offscreenPageLimit = restaurantCategories.size

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(restaurantCategories[position].categoryNameId)
        }.attach()
    }

    override fun observeData() {
    }

    companion object {

        fun newInstance() = HomeFragment()

        const val TAG = "HomeFragment"
    }
}