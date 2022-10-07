package com.example.baemin.widget.adapter.viewholder.retaurant

import com.example.baemin.R
import com.example.baemin.databinding.ViewholderRestaurantBinding
import com.example.baemin.extensions.clear
import com.example.baemin.extensions.load
import com.example.baemin.model.restaurant.RestaurantModel
import com.example.baemin.screen.base.BaseViewModel
import com.example.baemin.util.provider.ResourcesProvider
import com.example.baemin.widget.adapter.viewholder.ModelViewHolder
import com.example.baemin.widget.listener.AdapterListener
import com.example.baemin.widget.listener.restaurant.RestaurantListListener

class RestaurantViewHolder(
    private val binding: ViewholderRestaurantBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<RestaurantModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        restaurantImage.clear()
    }

    override fun bindData(model: RestaurantModel) {
        super.bindData(model)
        with (binding) {
            restaurantImage.load(model.restaurantImageUrl, 24f)
            restaurantTitleText.text = model.restaurantTitle
            gradeText.text = resourcesProvider.getString(R.string.grade_format, model.grade)
            reviewCountText.text = resourcesProvider.getString(R.string.review_count, model.reviewCount)
            val (minTime, maxTime) = model.deliveryTimeRange
            deliveryTimeText.text = resourcesProvider.getString(R.string.delivery_time, minTime, maxTime)
            val (minTip, maxTip) = model.deliveryTipRange
            deliveryTipText.text = resourcesProvider.getString(R.string.delivery_tip, minTip, maxTip)
        }
    }

    override fun bindViews(model: RestaurantModel, adapterListener: AdapterListener) = with(binding) {
        if (adapterListener is RestaurantListListener) {
            root.setOnClickListener {
                adapterListener.onClickItem(model)
            }
        }
    }
}