package com.example.city.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.city.R
import com.example.city.data.Recommendation

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
