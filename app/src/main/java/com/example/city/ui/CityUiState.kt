package com.example.city.ui

import com.example.city.data.CategoryType
import com.example.city.data.LocalRecommendationData
import com.example.city.data.Recommendation

data class CityUiState(
    val categoryRecommendations: Map<CategoryType, List<Recommendation>> = emptyMap(),
    val currentCategory: CategoryType = CategoryType.Coffes,
    val currentRecommendation: Recommendation = LocalRecommendationData.defaultRecommendation,
    val isShowingHomepage: Boolean = true
){
    val currentRecommendations: List<Recommendation> by lazy { categoryRecommendations[currentCategory]!! }
}