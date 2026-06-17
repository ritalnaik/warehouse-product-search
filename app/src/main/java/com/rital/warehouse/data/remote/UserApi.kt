package com.rital.warehouse.data.remote

import com.rital.warehouse.data.model.user.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {
    @GET("bolt/newuser.json")
    suspend fun getUser(): User

}