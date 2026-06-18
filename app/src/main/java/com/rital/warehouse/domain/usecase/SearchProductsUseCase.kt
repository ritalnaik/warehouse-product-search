package com.rital.warehouse.domain.usecase

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.search.ProductWithoutPrice
import com.rital.warehouse.domain.repository.ProductRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query: String): ResultState<List<ProductDetail>?> {
        return productRepository.searchProducts(query)
    }
}