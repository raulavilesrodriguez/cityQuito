package com.example.city.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.city.data.Category
import com.example.city.data.CategoryType
import com.example.city.R
import com.example.city.data.LocalCategoryDataProvider
import com.example.city.data.Recommendation
import com.example.city.ui.components.CategoryListItem
import com.example.city.ui.components.RecommendationListItem
import com.example.city.ui.theme.CityTheme

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
                        end = dimensionResource(id = R.dimen.padding_medium)
                    )
            )
        } else if(cityUiState.isShowingRecommendations){
            RecommendationsList(
                recommendations = cityUiState.currentRecommendations,
                onClick = {
                          onRecommendationPressed(it)
                },
                contentPadding = innerPadding
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
                 }
         )
        },
        navigationIcon =
        if(!cityUiState.isShowingHomepage and  !cityUiState.isShowingRecommendations){
            {
                IconButton(onClick = onDetailScreenBackPressed ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, 
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        } else if(!cityUiState.isShowingHomepage and  cityUiState.isShowingRecommendations){
            {
                IconButton(onClick = onRecommendationsBackPressed ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
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

@Composable
private fun CategoryList(
    categories: List<Category>,
    onClick:  (Category) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
){
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        items(categories, key = {category -> category.id}){category ->
            CategoryListItem(
                category = category,
                onItemClick = onClick
            )
        }
    }
}

@Composable
fun RecommendationsList(
    recommendations: List<Recommendation>,
    onClick: (Recommendation) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        items(recommendations, key = { recommendation -> recommendation.id}){recommendation ->
            RecommendationListItem(
                recommendation = recommendation,
                onItemClick = onClick
            )
        }
    }
}

@Preview
@Composable
fun CategoryListPreview(){
    CityTheme {
        Surface {
            CategoryList(
                categories = LocalCategoryDataProvider.allCategories,
                onClick = {},
            )
        }
    }
}