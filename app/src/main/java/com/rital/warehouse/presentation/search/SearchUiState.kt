package com.rital.warehouse.presentation.search

import com.rital.warehouse.data.model.search.ProductWithoutPrice

data class SearchUiState(
    val isLoading: Boolean = false,
    val products: List<ProductWithoutPrice?>? = emptyList(),
    val error: String? = null,
    val isEmpty: Boolean = false
)
