package com.rital.warehouse.data.remote

import com.rital.warehouse.data.model.user.LoginResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UserApi {
    @GET("twlYourWarehouseProd/Login.json")
    suspend fun login(
        @Header("Authorization") authorization: String = "Guest",
        @Header("X-TWL-Device") device: String = "Android"
    ): LoginResponse
}