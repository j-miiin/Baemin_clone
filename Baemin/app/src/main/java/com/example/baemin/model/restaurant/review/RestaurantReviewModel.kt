package com.example.baemin.model.restaurant.review

import android.net.Uri
import com.example.baemin.model.CellType
import com.example.baemin.model.Model

data class RestaurantReviewModel(
    override val id: Long,
    override val type: CellType = CellType.REVIEW_CELL,
    val title: String,
    val description: String,
    val grade: Int,
    val thumbnailImageUri: Uri ?= null
): Model(id, type)
