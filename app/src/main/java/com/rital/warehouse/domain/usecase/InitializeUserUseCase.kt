package com.rital.warehouse.domain.usecase

import android.util.Log
import com.rital.warehouse.domain.repository.UserRepository
import javax.inject.Inject

class InitializeUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() {
        val existingUserId = repository.getUserId()
        if (existingUserId.isNullOrEmpty()) {
            repository.fetchAndStoreUser()
        }
    }

}