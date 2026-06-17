package com.rital.warehouse.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rital.warehouse.core.Constants
import com.rital.warehouse.core.theme.Dimens
import com.rital.warehouse.data.model.search.ProductWithoutPrice

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onQueryChanged: (String) -> Unit,
    onProductClick: (String) -> Unit
) {
    var query by remember { mutableStateOf(Constants.EMPTY_STRING) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Medium)
    ) {
        Text(
            modifier = Modifier.clickable(onClick =
                { onProductClick("BARCODETEST") }
            ),
            text = "Warehouse Search",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(Dimens.ExtraSmall))

        Text(
            text = "Search products instantly",
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                onQueryChanged(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search products") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(Dimens.Medium))
        SearchContent(uiState)
    }
}

@Composable
private fun SearchContent(
    uiState: SearchUiState
) {
    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        uiState.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(uiState.error)
            }
        }
        uiState.isEmpty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No products found....!!")
            }
        }
        else -> {
            val products = uiState.products
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(Dimens.Medium),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.Small),
                verticalArrangement = Arrangement.spacedBy(Dimens.Small)
            ) {
                items(
                    count = products?.size ?: 0,
                ) { index ->
                    val product = products?.get(index)
                    product?.let {  ProductItem(it) }
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: ProductWithoutPrice
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = product.ImageURL,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.ImageHeight)
            )
            Column(
                modifier = Modifier.padding(Dimens.SmallMedium)
            ) {
                Text(
                    text = product.Description ?: Constants.EMPTY_STRING,
                    maxLines = 2
                )
                Spacer(
                    modifier = Modifier.height(Dimens.ExtraSmall)
                )
                Text(
                    text = "Code: ${product.ProductKey}"
                )
            }
        }
    }
}