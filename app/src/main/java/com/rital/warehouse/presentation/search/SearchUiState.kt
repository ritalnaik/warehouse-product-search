package com.rital.warehouse.presentation.search

import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.search.ProductWithoutPrice

data class SearchUiState(
    val isLoading: Boolean = false,
    val products: List<ProductDetail>? = emptyList(),
    val error: String? = null,
    val isEmpty: Boolean = false
)
