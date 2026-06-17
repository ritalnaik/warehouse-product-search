package com.rital.warehouse.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rital.warehouse.core.ResultState
import com.rital.warehouse.domain.usecase.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
    private val searchQuery = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .filter { it.isNotBlank() }
                .collectLatest { query ->
                    searchProducts(query)
                }
        }
    }

    fun onQueryChanged(query: String) {
        searchQuery.value = query
    }
    fun searchProducts(query: String) {
        if (query.isBlank()) {
            _uiState.value = SearchUiState()
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null,
                    isEmpty = false
                )
            }

            when (val result = searchProductsUseCase(query)) {
                is ResultState.Success -> {
                    val products = result.data
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = products,
                            error = null,
                            isEmpty = products?.isEmpty() == true
                        )
                    }
                }

                is ResultState.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = emptyList(),
                            error = result.message,
                            isEmpty = false
                        )
                    }
                }

                is ResultState.Empty -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = emptyList(),
                            error = null,
                            isEmpty = true
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
            }
        }
    }
}