package com.rital.warehouse.data.remote


import com.rital.warehouse.core.Constants
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.product.ProductDetailsList
import retrofit2.http.GET
import retrofit2.http.Query

interface WarehouseApi {
    @GET("twlYourWarehouseProd/search.json")
    suspend fun getSearchResult(
        @Query("Search") searchTerm: String,
        @Query("UserID") userID: String? = Constants.EMPTY_STRING,
        @Query("Start") Start: String = "0",
        @Query("Limit") Limit: String = "10"
    ): ProductDetailsList

    @GET("twlYourWarehouseProd/product.json")
    suspend fun getProductDetails(
        @Query("ProductId") barCode: String? = Constants.EMPTY_STRING,
        @Query("UserID") userID: String? = Constants.EMPTY_STRING
    ): ProductDetail
}