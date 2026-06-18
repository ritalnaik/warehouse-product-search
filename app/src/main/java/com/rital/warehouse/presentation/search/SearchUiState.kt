package com.rital.warehouse.presentation.search

import com.rital.warehouse.data.model.product.SearchResults

data class SearchUiState(
    val isLoading: Boolean = false,
    val products: List<SearchResults>? = emptyList(),
    val error: String? = null,
    val isEmpty: Boolean = false
)
