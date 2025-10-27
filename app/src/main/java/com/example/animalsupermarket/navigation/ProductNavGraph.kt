package com.example.animalsupermarket.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.animalsupermarket.screens.category.CategoryScreen
import com.example.animalsupermarket.screens.details.CommentsScreen
import com.example.animalsupermarket.screens.details.ProductDetailsScreen
import com.example.animalsupermarket.viewmodel.CartViewModel
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import com.example.animalsupermarket.viewmodel.ProductDetailsViewModel

fun NavGraphBuilder.productNavGraph(
    navController: NavController,
    cartViewModel: CartViewModel,
    favoritesViewModel: FavoritesViewModel
) {
    navigation(
        startDestination = "productDetails",
        route = "product"
    ) {
        composable(
            "category/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            if (category != null) {
                val categoryViewModel: CategoryViewModel = viewModel()
                CategoryScreen(navController, categoryViewModel, cartViewModel)
            }
        }
        composable(
            "productDetails/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                val productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
                val historyViewModel: HistoryViewModel = hiltViewModel()
                ProductDetailsScreen(
                    navController = navController,
                    productId = productId,
                    productDetailsViewModel = productDetailsViewModel,
                    cartViewModel = cartViewModel,
                    favoritesViewModel = favoritesViewModel,
                    historyViewModel = historyViewModel
                )
            }
        }
        composable(
            "comments/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            if (productId != null) {
                CommentsScreen(navController, productId)
            }
        }
    }
}