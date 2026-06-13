package com.rital.warehouse.core

sealed class ResultState<out T> {
    data object Loading : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(
        val message: String,
        val throwable: Throwable? = null
    ) : ResultState<Nothing>()
    data object Empty : ResultState<Nothing>()
}