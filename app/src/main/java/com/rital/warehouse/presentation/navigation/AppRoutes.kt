package com.rital.warehouse.presentation.navigation

sealed class AppRoutes(val route: String) {
    data object Search : AppRoutes("search")
    data object ProductDetails : AppRoutes("product_details/{barcode}") {
        fun createRoute(barcode: String): String {
            return "product_details/$barcode"
        }
    }
}