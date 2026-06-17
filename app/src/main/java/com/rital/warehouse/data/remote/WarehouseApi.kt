package com.rital.warehouse.data.remote


import com.rital.warehouse.core.Constants
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.search.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface WarehouseApi {

    @GET("bolt/search.json")
    suspend fun getSearchResult(
        @Query("Search") searchTerm: String,
        @Query("MachineID") machineId: String? = Constants.MACHINE_ID,
        @Query("UserID") userID: String? = Constants.EMPTY_STRING,
        @Query("Branch") branch: Int? = Constants.BRANCH_ID,
        @Query("Start") Start: String = "0",
        @Query("Limit") Limit: String = "10"
    ): SearchResult

    @GET("bolt/price.json")
    suspend fun getProductDetails(
        @Query("BarCode") barCode: String? = Constants.EMPTY_STRING,
        @Query("MachineID") machineID: String? = Constants.EMPTY_STRING,
        @Query("UserID") userID: String? = Constants.EMPTY_STRING,
        @Query("Branch") branch: String? = Constants.EMPTY_STRING
    ): ProductDetail
}