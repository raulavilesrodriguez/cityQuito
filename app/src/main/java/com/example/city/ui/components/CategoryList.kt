package com.example.city.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.city.R
import com.example.city.data.Category
import com.example.city.data.LocalCategoryDataProvider
import com.example.city.ui.theme.CityTheme

@Composable
fun CategoryList(
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