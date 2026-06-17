package com.rital.warehouse.presentation.details

import com.rital.warehouse.data.model.product.ProductDetail

data class ProductUiState (
    val isLoading: Boolean = false,
    val product: ProductDetail? = null,
    val error: String? = null
    )
