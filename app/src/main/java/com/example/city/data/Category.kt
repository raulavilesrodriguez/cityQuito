package com.example.city.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val id: Long,
    @StringRes
    val categoryName: Int,
    @StringRes
    val categoryDescription: Int,
    @DrawableRes
    val avatar: Int,
    var category: CategoryType = CategoryType.Banks,
)