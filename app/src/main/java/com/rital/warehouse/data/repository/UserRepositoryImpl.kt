package com.rital.warehouse.data.repository

import android.util.Log
import com.rital.warehouse.data.local.UserPreferences
import com.rital.warehouse.data.remote.UserApi
import com.rital.warehouse.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val userPreferences: UserPreferences
) : UserRepository {

    override suspend fun fetchAndStoreUser() {
        val response = userApi.getUser()
        userPreferences.saveUserId(response.UserID ?: "")
    }

    override suspend fun getUserId(): String {
        return userPreferences.getUserId()
    }
}