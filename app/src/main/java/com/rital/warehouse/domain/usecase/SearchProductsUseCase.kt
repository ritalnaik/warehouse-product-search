package com.rital.warehouse.domain.usecase

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.SearchResults
import com.rital.warehouse.domain.repository.ProductRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query: String): ResultState<List<SearchResults>?> {
        return productRepository.searchProducts(query)
    }
}