package com.example.city.data

import com.example.city.R

object LocalCategoryDataProvider {

    private val allCategories = listOf(
        Category(
            id = 2L,
            categoryName = R.string.coffes,
            categoryDescription = R.string.coffes_description,
            avatar = R.drawable.coffes,
            category = CategoryType.Coffes
        ),
        Category(
            id = 1L,
            categoryName = R.string.banks,
            categoryDescription = R.string.banks_description,
            avatar = R.drawable.banks,
            category = CategoryType.Banks
        ),
        Category(
            id = 3L,
            categoryName = R.string.parks,
            categoryDescription = R.string.parks_description,
            avatar = R.drawable.parks,
            category = CategoryType.Parks
        ),
        Category(
            id = 4L,
            categoryName = R.string.restaurants,
            categoryDescription = R.string.restaurants_description,
            avatar = R.drawable.restaurants,
            category = CategoryType.Restaurants
        ),
        Category(
            id = 5L,
            categoryName = R.string.shoppings,
            categoryDescription = R.string.shoppings_description,
            avatar = R.drawable.shoppings,
            category = CategoryType.Malls
        ),
    )

    /**
     * Get  category by id
     */
    fun getCategoryById(categoryId: Long): Category {
        return allCategories.firstOrNull {it.id == categoryId}
            ?: allCategories.first()
    }

    val defaultCategory = allCategories.first()
}