package com.rital.warehouse.domain.usecase

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository) {
    suspend operator fun invoke(barcode: String): ResultState<ProductDetail> {
        return productRepository.getProductDetails(barcode)
    }
}