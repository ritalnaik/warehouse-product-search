package com.rital.warehouse.presentation.search

import com.rital.warehouse.core.ResultState
import com.rital.warehouse.data.model.search.ProductWithoutPrice
import com.rital.warehouse.domain.usecase.SearchProductsUseCase
import com.rital.warehouse.presentation.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var useCase: SearchProductsUseCase
    private lateinit var viewModel: SearchViewModel
    @Before
    fun setup() {
        useCase = mockk()
        viewModel = SearchViewModel(useCase)
    }

    @Test
    fun searchProducts_blankQuery_resetsState() = runTest {
        viewModel.searchProducts("")
        assertFalse(viewModel.uiState.value.isLoading)
        assertTrue(viewModel.uiState.value.products.isNullOrEmpty())
    }

    @Test
    fun searchProducts_success_updatesUiState() = runTest {
        val products =listOf(ProductWithoutPrice())

        coEvery {
            useCase("milk")
        } returns ResultState.Success(products)

        viewModel.searchProducts("milk")
        advanceUntilIdle()
        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals(products, state.products)
        assertNull(state.error)
    }

    @Test
    fun searchProducts_error_updatesErrorState() = runTest {

        coEvery {
            useCase("milk")
        } returns ResultState.Error("Something went wrong")

        viewModel.searchProducts("milk")

        advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals(
            "Something went wrong",
            state.error
        )
    }

    @Test
    fun searchProducts_loading_updatesLoadingState() = runTest {
        coEvery {
            useCase("milk")
        } returns ResultState.Loading

        viewModel.searchProducts("milk")

        advanceUntilIdle()

        assertTrue(
            viewModel.uiState.value.isLoading
        )
    }

    @Test
    fun onQueryChanged_debounce_callsLatestQueryOnly() = runTest {
        coEvery {
            useCase(any())
        } returns ResultState.Empty
        viewModel.onQueryChanged("m")
        viewModel.onQueryChanged("mi")
        viewModel.onQueryChanged("mil")
        viewModel.onQueryChanged("milk")
        advanceTimeBy(600)
        coVerify(exactly = 1) {
            useCase("milk")
        }
    }
}