package com.rital.warehouse.presentation.details

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.product.ProductDetail
import com.rital.warehouse.domain.usecase.GetProductDetailUseCase
import com.rital.warehouse.domain.usecase.SearchProductsUseCase
import com.rital.warehouse.presentation.search.SearchViewModel
import com.rital.warehouse.presentation.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var useCase: GetProductDetailUseCase
    private lateinit var viewModel: ProductDetailsViewModel
    @Before
    fun setup() {
        useCase = mockk()
        viewModel = ProductDetailsViewModel(useCase)
    }
    @Test
    fun getProductDetails_blankBarcode_test() = runTest {
        viewModel.getProductDetails("")
        assertFalse(viewModel.uiState.value.isLoading)
        assertTrue(viewModel.uiState.value.product == null)
    }

    @Test
    fun getProductDetails_success() = runTest {
        val productDetail = ProductDetail()
        coEvery {
            useCase("123456789012")
        } returns ResultState.Success(productDetail)

        viewModel.getProductDetails("123456789012")
        advanceUntilIdle()
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(productDetail, state.product)
        assertNull(state.error)
    }
}