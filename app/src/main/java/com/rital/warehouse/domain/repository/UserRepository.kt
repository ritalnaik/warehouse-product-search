package com.rital.warehouse.domain.repository


interface UserRepository {
    suspend fun fetchAndStoreUser()
    suspend fun getUserId(): String?
}