package com.rital.warehouse.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rital.warehouse.domain.usecase.InitializeUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val initializeUserUseCase: InitializeUserUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            try {
              initializeUserUseCase()
            } catch (e: Exception) {
                Log.e("MainViewModel", "User initialization failed", e)
            }
        }
    }
}