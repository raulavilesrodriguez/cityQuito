package com.example.city.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.city.R
import com.example.city.data.LocalRecommendationData
import com.example.city.data.Recommendation
import com.example.city.ui.theme.CityTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationListItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
        onClick = { onItemClick(recommendation)}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.card_image_height))
        ) {
            ListImageItem(
                recommendation = recommendation,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(id = R.dimen.padding_small),
                        horizontal = dimensionResource(id = R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = recommendation.recommendationName),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(id = recommendation.smallDescription),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}

@Composable
private fun ListImageItem(recommendation: Recommendation, modifier: Modifier){
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = recommendation.imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillHeight
        )
    }
}

@Preview
@Composable
fun RecommendationListItemPreview(){
    CityTheme {
        RecommendationListItem(
            recommendation = LocalRecommendationData.defaultRecommendation,
            onItemClick = {}
        )
    }
}