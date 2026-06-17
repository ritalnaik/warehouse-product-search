package com.rital.warehouse.data.repository

import android.util.Log
import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.search.ProductWithoutPrice
import com.rital.warehouse.data.model.search.SearchResult
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
    private val productList = mutableListOf<ProductWithoutPrice?>()
    override suspend fun searchProducts(query: String): ResultState<List<ProductWithoutPrice?>> {
        return try {
            if (networkManager.isConnected()) {
                val response = warehouseApi.getSearchResult(searchTerm = query, userID = userRepository.getUserId())

                if(response.Found.equals("Y")){
                   val totalItemNum = response.HitCount
                   val size = response.Results?.size?:0
                   for (i in 0..size) {
                       val item = response.Results?.get(i)
                       item?.Products?.let {
                           productList.addAll(it)
                       }
                   }
                   ResultState.Success(productList)
                }
                else ResultState.Error("Search failed!")
            } else  ResultState.Error("No internet connection")

        } catch (e: Exception) {
            ResultState.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getProductDetails(barcode: String): ResultState<ProductDetail> {
        return try {
            val response = warehouseApi.getProductDetails(barcode)
            ResultState.Success(response)
        } catch (e: Exception) {
            ResultState.Error(e.message ?: "An error occurred")
        }
    }
}
