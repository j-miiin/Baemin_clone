package com.example.baemin.widget.adapter.viewholder.review

import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.baemin.R
import com.example.baemin.databinding.ViewholderRestaurantReviewBinding
import com.example.baemin.extensions.clear
import com.example.baemin.extensions.load
import com.example.baemin.model.restaurant.RestaurantModel
import com.example.baemin.model.restaurant.review.RestaurantReviewModel
import com.example.baemin.screen.base.BaseViewModel
import com.example.baemin.util.provider.ResourcesProvider
import com.example.baemin.widget.adapter.viewholder.ModelViewHolder
import com.example.baemin.widget.listener.AdapterListener
import com.example.baemin.widget.listener.restaurant.RestaurantListListener

class RestaurantReviewViewHolder(
    private val binding: ViewholderRestaurantReviewBinding,
    viewModel: BaseViewModel,
    resourcesProvider: ResourcesProvider
): ModelViewHolder<RestaurantReviewModel>(binding, viewModel, resourcesProvider) {

    override fun reset() = with(binding) {
        reviewThumbnailImage.clear()
        reviewThumbnailImage.isGone = true
    }

    override fun bindData(model: RestaurantReviewModel) {
        super.bindData(model)
        with (binding) {
            if (model.thumbnailImageUri != null) {
                reviewThumbnailImage.isVisible = true
                reviewThumbnailImage.load(model.thumbnailImageUri.toString())
            } else {
                reviewThumbnailImage.isGone = true
            }
            reviewTitleText.text = model.title
            reviewText.text = model.description
            ratingBar.rating = model.grade
        }
    }

    override fun bindViews(model: RestaurantReviewModel, adapterListener: AdapterListener) = Unit
}