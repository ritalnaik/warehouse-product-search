package com.rital.warehouse.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rital.warehouse.core.ResultState
import com.rital.warehouse.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState = _uiState.asStateFlow()


    fun getProductDetails(barcode: String) {
        if (barcode.isBlank()) {
            _uiState.value = ProductUiState()
            return
        }

        viewModelScope.launch {
            when (val result = getProductDetailUseCase(barcode)) {
                is ResultState.Success -> {
                    val product = result.data
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            product = product,
                            error = null
                        )
                    }
                }

                is ResultState.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

                is ResultState.Loading -> {
                    _uiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                else -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Unknown error"
                        )
                    }
                }}
            }
        }
    }
