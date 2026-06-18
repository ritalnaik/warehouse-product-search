package com.rital.warehouse.presentation.search

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rital.warehouse.core.Constants
import com.rital.warehouse.core.theme.Dimens
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.product.SearchResults
import com.rital.warehouse.data.model.search.ProductWithoutPrice
import com.rital.warehouse.presentation.navigation.AppRoutes

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
        SearchContent(uiState,onProductClick)
    }
}

@Composable
private fun SearchContent(
    uiState: SearchUiState,
    onProductClick: (String) -> Unit = {}
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
                contentPadding = PaddingValues(Dimens.Small),
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.ExtraSmall),
                verticalArrangement = Arrangement.spacedBy(Dimens.ExtraSmall)
            ) {
                items(
                    count = products?.size ?: 0,
                ) { index ->
                    val product = products?.get(index)
                    product?.let {  ProductItem(it, onProductClick) }
                }
            }
        }
    }
}

//@Composable
//fun ProductItem(
//    product: SearchResults,
//    onProductClick: (String) -> Unit
//) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { onProductClick(product.productId) },
//
//        ) {
//            AsyncImage(
//                model = product.productImageUrl,
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(Dimens.ImageHeight)
//            )
//            Column(
//                modifier = Modifier.padding(Dimens.SmallMedium)
//            ) {
//                Text(
//                    text = product.productName ?: Constants.EMPTY_STRING,
//                    maxLines = 2
//                )
//                Spacer(
//                    modifier = Modifier.height(Dimens.ExtraSmall)
//                )
//                Text(
//                    text = "Code: ${product.brandDescription}"
//                )
//                Text(
//                    text = "$ "+product.priceInfo.price.toString(),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    color = Color.Black,
//                    modifier = Modifier.fillMaxWidth()
//                )
//            }
//        }
//}

@Composable
fun ProductItem(
    product: SearchResults,
    onProductClick: (String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onProductClick(product.productId ?: "")
            },
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.productImageUrl)
                        .crossfade(true)
                        .build(),
//                    placeholder = painterResource(R.drawable.ic_placeholder),
//                    error = painterResource(R.drawable.ic_placeholder),
                    contentDescription = product.productName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )

                Surface(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopStart),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = product.brandDescription ?: "",
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 4.dp
                        ),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Text(
                    text = product.productName ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = product.productDescription ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            text = "$ ${product.priceInfo.price}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(
                                horizontal = 12.dp,
                                vertical = 6.dp
                            )
                        )
                    }

                    Text(
                        text = "View",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}