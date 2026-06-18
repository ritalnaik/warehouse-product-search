package com.rital.warehouse.data.repository

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.Product
import com.rital.warehouse.data.model.product.SearchResults
import com.rital.warehouse.data.network.NetworkManager
import com.rital.warehouse.data.remote.WarehouseApi
import com.rital.warehouse.domain.repository.ProductRepository
import com.rital.warehouse.domain.repository.UserRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val warehouseApi: WarehouseApi,
    private val networkManager: NetworkManager,
    private val userRepository: UserRepository
): ProductRepository {
    override suspend fun searchProducts(query: String): ResultState<List<SearchResults>> {
        return try {
            if (networkManager.isConnected()) {
                val response = warehouseApi.getSearchResult(searchTerm = query, userID = userRepository.getUserId())
                ResultState.Success(response.products)
            } else  ResultState.Error("No internet connection")

        } catch (e: Exception) {
            ResultState.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getProductDetails(barcode: String): ResultState<Product> {
        return try {
            val response = warehouseApi.getProductDetails(barcode)
            ResultState.Success(response)
        } catch (e: Exception) {
            ResultState.Error(e.message ?: "An error occurred")
        }
    }
}
