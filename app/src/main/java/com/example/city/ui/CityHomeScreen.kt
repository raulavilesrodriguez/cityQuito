package com.example.city.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.city.data.Category
import com.example.city.data.CategoryType
import com.example.city.data.Recommendation
import com.example.city.ui.utils.ContentType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityHomeScreen(
    contentType: ContentType,
    cityUiState: CityUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    onRecommendationsBackPressed: () -> Unit,
    modifier: Modifier = Modifier
){
    if(contentType == ContentType.ListOnly){
        CategoryListOnlyContent(
            cityUiState = cityUiState,
            onCategoryPressed = onCategoryPressed,
            onRecommendationPressed = onRecommendationPressed,
            onDetailScreenBackPressed = onDetailScreenBackPressed,
            onRecommendationsBackPressed = onRecommendationsBackPressed,
        )
    } else {
        CityFullContent(
            cityUiState = cityUiState,
            onCategoryPressed = onCategoryPressed,
            onRecommendationPressed = onRecommendationPressed,
        )
    }
}