package com.example.city.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.city.data.Category
import com.example.city.R
import com.example.city.data.LocalCategoryDataProvider
import com.example.city.data.Recommendation
import com.example.city.ui.components.CategoryList
import com.example.city.ui.components.RecommendationDetail
import com.example.city.ui.components.RecommendationsList


@Composable
fun CategoryListOnlyContent(
    cityUiState: CityUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    onDetailScreenBackPressed: () -> Unit,
    onRecommendationsBackPressed: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            CityAppBar(
                onDetailScreenBackPressed = onDetailScreenBackPressed,
                onRecommendationsBackPressed = onRecommendationsBackPressed,
                cityUiState =cityUiState
            )
        }
    ) {innerPadding ->
        if(cityUiState.isShowingHomepage){
            CategoryList(
                categories = cityUiState.categoriesList,
                onClick = {
                         onCategoryPressed(it)
                },
                contentPadding = innerPadding,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_medium),
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
            )
        } else if(cityUiState.isShowingRecommendations){
            RecommendationsList(
                recommendations = cityUiState.currentRecommendations,
                onClick = {
                          onRecommendationPressed(it)
                },
                contentPadding = innerPadding,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_medium),
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
            )
        } else {
            RecommendationDetail(
                selectedRecommendation = cityUiState.currentRecommendation,
                contentPadding = innerPadding,
                onBackPressed = {
                    onDetailScreenBackPressed()
                },
                modifier = modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_medium),
                        start = dimensionResource(id = R.dimen.padding_medium),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    onDetailScreenBackPressed: () -> Unit,
    onRecommendationsBackPressed: () -> Unit,
    cityUiState: CityUiState,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
         Text(
             text = 
                 if(!cityUiState.isShowingHomepage and  !cityUiState.isShowingRecommendations){
                     stringResource(id = cityUiState.currentRecommendation.recommendationName)
                 } else if (!cityUiState.isShowingHomepage and  cityUiState.isShowingRecommendations){
                     stringResource(id = LocalCategoryDataProvider.getCategoryByType(cityUiState.currentCategory).categoryName)
                 } else {
                     stringResource(id = R.string.app_name)
                 },
             color = MaterialTheme.colorScheme.onPrimary
         )
        },
        navigationIcon =
        if(!cityUiState.isShowingHomepage and  !cityUiState.isShowingRecommendations){
            {
                IconButton(onClick = onDetailScreenBackPressed ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, 
                        contentDescription = stringResource(id = R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        } else if(!cityUiState.isShowingHomepage and  cityUiState.isShowingRecommendations){
            {
                IconButton(onClick = onRecommendationsBackPressed ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
        else {
            {Box{}}
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}









