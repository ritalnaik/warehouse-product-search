package com.rital.warehouse.data.remote

import com.rital.warehouse.core.BRANCH_ID
import com.rital.warehouse.core.MACHINE_ID
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.data.model.search.SearchResult
import com.rital.warehouse.data.model.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WarehouseApi {

    @GET("bolt/search.json")
    suspend fun getSearchResult(
        @Query("Search") searchTerm: String,
        @Query("MachineID") machineId: String? = MACHINE_ID,
        @Query("UserID") userID: String? = "",
        @Query("Branch") branch: Int? = BRANCH_ID,
        @Query("Start") Start: String = "0",
        @Query("Limit") Limit: String = "10"
    ): SearchResult

    @GET("bolt/price.json")
    suspend fun getProductDetails(
        @Query("BarCode") barCode: String? = "",
        @Query("MachineID") machineID: String? = "",
        @Query("UserID") userID: String? = "",
        @Query("Branch") branch: String? = ""
    ): ProductDetail
}