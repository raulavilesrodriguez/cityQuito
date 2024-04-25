package com.example.city.ui

import androidx.lifecycle.ViewModel
import com.example.city.data.CategoryType
import com.example.city.data.LocalRecommendationData
import com.example.city.data.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CityUiState())
    val uiState: StateFlow<CityUiState> = _uiState

    init {
        initializeUIState()
    }
    private fun initializeUIState(){
        val categoryRecommendations: Map<CategoryType, List<Recommendation>> =
            LocalRecommendationData.allRecommendations.groupBy { it.category }
        _uiState.value = CityUiState(
            categoryRecommendations = categoryRecommendations,
            currentRecommendation = categoryRecommendations[CategoryType.Coffes]?.get(0)
                ?: LocalRecommendationData.defaultRecommendation
        )
    }

    fun updateCurrentCategory(categoryType: CategoryType){
        _uiState.update {
            it.copy(
                currentCategory = categoryType,
                isShowingHomepage = false
            )
        }
    }

    fun updateDetailRecommendation(selectedRecommendation: Recommendation){
        _uiState.update {
            it.copy(
                currentRecommendation = selectedRecommendation,
                isShowingHomepage = false
            )
        }
    }

    fun resetHomeScreenStates(){
        _uiState.update {
            it.copy(
                currentRecommendation = it.categoryRecommendations[it.currentCategory]?.get(0)
                    ?: LocalRecommendationData.defaultRecommendation,
                isShowingHomepage = true
            )
        }
    }
}