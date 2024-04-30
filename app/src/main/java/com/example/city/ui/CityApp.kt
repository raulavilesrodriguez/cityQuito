package com.example.city.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.city.data.Category
import com.example.city.data.CategoryType
import com.example.city.data.Recommendation
import com.example.city.ui.utils.ContentType

@Composable
fun CityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
){
    val contentType: ContentType
    val viewModel: CityViewModel = viewModel()
    val cityUiState = viewModel.uiState.collectAsState().value

    when(windowSize){
        WindowWidthSizeClass.Compact -> {
            contentType = ContentType.ListOnly
        }
        WindowWidthSizeClass.Medium -> {
            contentType = ContentType.ListOnly
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = ContentType.ListAndDetail
        }
        else -> {
            contentType = ContentType.ListOnly
        }
    }
    CityHomeScreen(
        contentType = contentType,
        cityUiState = cityUiState,
        onCategoryPressed = { category: Category ->
            viewModel.updateCurrentCategory(categoryType = category)
            viewModel.resetRecommendationScreenStates()

        },
        onRecommendationPressed = { recommendation: Recommendation ->
            viewModel.updateDetailRecommendation(
                selectedRecommendation = recommendation
            )
        },
        onDetailScreenBackPressed = {
            viewModel.resetRecommendationScreenStates()
        },
        onRecommendationsBackPressed = {
            viewModel.resetCategoriesScreenStates()
        },
        modifier = modifier
    )

}