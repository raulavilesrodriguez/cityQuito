package com.example.city.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.city.R

data class Recommendation(
    val id: Long,
    var category: CategoryType = CategoryType.Banks,
    @StringRes val recommendationName: Int,
    @StringRes val smallDescription: Int = R.string.item_list_subtitle,
    @StringRes val recommendationDescription: Int,
    @DrawableRes val imageResourceId: Int
)
