package com.example.baemin.widget.adapter.viewholder.order

import com.example.baemin.R
import com.example.baemin.databinding.ViewholderOrderBinding
import com.example.baemin.model.order.OrderModel
import com.example.baemin.screen.base.BaseViewModel
import com.example.baemin.util.provider.ResourcesProvider
import com.example.baemin.widget.adapter.viewholder.ModelViewHolder
import com.example.baemin.widget.listener.AdapterListener
import com.example.baemin.widget.listener.order.OrderListListener

class OrderViewHolder(
    private val binding: ViewholderOrderBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<OrderModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = Unit

    override fun bindData(model: OrderModel) {
        super.bindData(model)
        with(binding) {
            orderTitleText.text = resourcesProvider.getString(R.string.order_history_title, model.orderId)

            val foodMenuList = model.foodMenuList

            foodMenuList
                .groupBy { it.title }
                .entries.forEach { (title, menuList) ->
                    val orderDataStr =
                        orderContentText.text.toString() + "메뉴 : $title | 가격 : ${menuList.first().price}원 X ${menuList.size}\n"
                    orderContentText.text = orderDataStr
                }
            orderContentText.text = orderContentText.text.trim()

            orderTotalPriceText.text =
                resourcesProvider.getString(
                    R.string.price,
                    foodMenuList.map { it.price }.reduce { total, price -> total + price}
                )
        }
    }

    override fun bindViews(model: OrderModel, adapterListener: AdapterListener) {

        if (adapterListener is OrderListListener) {
            binding.root.setOnClickListener {
                adapterListener.writeRestaurantReview(model.orderId, model.restaurantTitle)
            }
        }
    }
}