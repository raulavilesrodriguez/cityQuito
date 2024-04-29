package com.example.city.ui

import android.app.Activity
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.city.R
import com.example.city.data.Category
import com.example.city.data.Recommendation
import com.example.city.ui.components.CategoryList
import com.example.city.ui.components.RecommendationDetail
import com.example.city.ui.components.RecommendationsList
import com.example.city.ui.theme.CityTheme

@Composable
fun CityFullContent(
    cityUiState: CityUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
){
    Scaffold(
        topBar = {
            CityAppBarFull()
        }
    ) {innerPadding ->
        CategoriesRecommendationsDetails(
            cityUiState = cityUiState,
            onCategoryPressed = onCategoryPressed,
            onRecommendationPressed = onRecommendationPressed,
            contentPadding = innerPadding
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBarFull(
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
                },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}

@Composable
private fun CategoriesRecommendationsDetails(
    cityUiState: CityUiState,
    onCategoryPressed: (Category) -> Unit,
    onRecommendationPressed: (Recommendation) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
){
    Row(modifier = modifier) {
        CategoryList(
            categories = cityUiState.categoriesList,
            onClick = {
                onCategoryPressed(it)
            },
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(1f)
                .padding(
                    top = dimensionResource(id = R.dimen.padding_medium),
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_medium),
                    bottom = dimensionResource(id = R.dimen.padding_medium)
                )
        )
        RecommendationsList(
            recommendations = cityUiState.currentRecommendations,
            onClick = {
                onRecommendationPressed(it)
            },
            contentPadding = contentPadding,
            modifier = Modifier
                .weight(1f)
                .padding(
                    top = dimensionResource(id = R.dimen.padding_medium),
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_medium),
                    bottom = dimensionResource(id = R.dimen.padding_medium)
                )
        )
        val activity = LocalContext.current as Activity
        RecommendationDetail(
            selectedRecommendation = cityUiState.currentRecommendation,
            contentPadding = contentPadding,
            onBackPressed = {
                activity.finish()
            },
            modifier = Modifier
                .weight(1f)
                .padding(
                    top = dimensionResource(id = R.dimen.padding_medium),
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_medium),
                    bottom = dimensionResource(id = R.dimen.padding_medium)
                )
        )
    }
}

