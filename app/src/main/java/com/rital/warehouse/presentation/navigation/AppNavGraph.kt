package com.rital.warehouse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.rital.warehouse.core.BARCODE_KEY
import com.rital.warehouse.presentation.details.ProductDetailScreen
import com.rital.warehouse.presentation.details.ProductDetailsViewModel
import com.rital.warehouse.presentation.search.SearchScreen
import com.rital.warehouse.presentation.search.SearchViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Search.route
    ) {
        composable(route = AppRoutes.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SearchScreen(
                uiState = uiState,
                onQueryChanged = viewModel::onQueryChanged,
                //below mentioned onclick listener is for testing purpose
                onProductClick = { barcode->
                    navController.navigate(AppRoutes.ProductDetails.createRoute(barcode))
                }
            )
        }

        composable(
            route = AppRoutes.ProductDetails.route,
            arguments = listOf(
                navArgument(BARCODE_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            val barcode = it.arguments?.getString(BARCODE_KEY) ?: ""
            val viewModel: ProductDetailsViewModel = hiltViewModel()
            ProductDetailScreen(
                barcode = barcode,
                viewModel = viewModel
            )
        }
    }
}