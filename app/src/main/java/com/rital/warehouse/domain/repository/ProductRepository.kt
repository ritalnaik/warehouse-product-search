package com.rital.warehouse.domain.repository

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.search.ProductWithoutPrice
import com.rital.warehouse.data.model.search.SearchResult

interface ProductRepository {
    suspend fun searchProducts(
        query: String
    ): ResultState<List<ProductWithoutPrice?>?>

    suspend fun getProductDetails(
        barcode: String
    ): ResultState<ProductDetail>
}