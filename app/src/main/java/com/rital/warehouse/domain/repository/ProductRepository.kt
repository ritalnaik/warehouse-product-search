package com.rital.warehouse.domain.repository

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.Product
import com.rital.warehouse.data.model.product.SearchResults

interface ProductRepository {
    suspend fun searchProducts(
        query: String
    ): ResultState<List<SearchResults>?>

    suspend fun getProductDetails(
        barcode: String
    ): ResultState<Product>
}