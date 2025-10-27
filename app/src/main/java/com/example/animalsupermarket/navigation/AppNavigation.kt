package com.example.animalsupermarket.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalsupermarket.api.ApiService
import com.example.animalsupermarket.screens.cart.CartScreen
import com.example.animalsupermarket.screens.favorites.FavoritesScreen
import com.example.animalsupermarket.screens.auth.LoginScreen
import com.example.animalsupermarket.screens.auth.RegisterScreen
import com.example.animalsupermarket.screens.category.CategoryScreen
import com.example.animalsupermarket.screens.home.HomeScreen
import com.example.animalsupermarket.screens.order.OrderScreen
import com.example.animalsupermarket.screens.profile.ProfileScreen
import com.example.animalsupermarket.screens.profile.AddressScreen
import com.example.animalsupermarket.screens.profile.SettingsScreen
import com.example.animalsupermarket.screens.profile.CouponsScreen
import com.example.animalsupermarket.screens.profile.AddEditAddressScreen
import com.example.animalsupermarket.screens.details.CommentsScreen
import com.example.animalsupermarket.screens.profile.AccountSecurityScreen
import com.example.animalsupermarket.screens.profile.NotificationSettingsScreen
import com.example.animalsupermarket.screens.profile.PrivacyScreen
import com.example.animalsupermarket.screens.profile.GeneralSettingsScreen
import com.example.animalsupermarket.screens.profile.AboutUsScreen
import com.example.animalsupermarket.screens.profile.HistoryScreen
import com.example.animalsupermarket.screens.search.SearchScreen
import com.example.animalsupermarket.viewmodel.CartViewModel
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import com.example.animalsupermarket.viewmodel.ProfileViewModel
import com.example.animalsupermarket.viewmodel.AddressViewModel
import com.example.animalsupermarket.viewmodel.CouponsViewModel
import com.example.animalsupermarket.viewmodel.HistoryViewModel
import com.example.animalsupermarket.screens.welcome.WelcomeScreen
import com.example.animalsupermarket.screens.details.ProductDetailsScreen
import com.example.animalsupermarket.viewmodel.AuthViewModel
import com.example.animalsupermarket.viewmodel.HomeViewModel
import com.example.animalsupermarket.viewmodel.ProductDetailsViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    val cartViewModel: CartViewModel = hiltViewModel()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()
    val addressViewModel: AddressViewModel = hiltViewModel()
    val couponsViewModel: CouponsViewModel = hiltViewModel()
    val historyViewModel: HistoryViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "welcome", modifier = modifier) {
        composable("welcome") {
            WelcomeScreen(navController, authViewModel)
        }
        composable("home") {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController, homeViewModel, favoritesViewModel)
        }
        composable("favorites") {
            FavoritesScreen(favoritesViewModel, navController)
        }
        composable("cart") {
            CartScreen(cartViewModel, navController)
        }
        composable("profile") {
            ProfileScreen(profileViewModel, navController, authViewModel)
        }
        composable("search") {
            SearchScreen(navController, viewModel(), favoritesViewModel)
        }
        composable("category/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            if (category != null) {
                CategoryScreen(navController, viewModel(), favoritesViewModel)
            }
        }
        composable("login") {
            LoginScreen(navController, authViewModel, profileViewModel)
        }
        composable("register") {
            RegisterScreen(navController, authViewModel, profileViewModel)
        }
        composable("orders") {
            OrderScreen(navController)
        }
        composable("productDetails/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt()
            if (productId != null) {
                val productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
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
        composable("address") {
            AddressScreen(navController, addressViewModel)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
        composable("coupons") {
            CouponsScreen(couponsViewModel)
        }
        composable("addEditAddress") {
            AddEditAddressScreen(navController, addressViewModel)
        }
        composable("addEditAddress/{addressId}") { backStackEntry ->
            val addressId = backStackEntry.arguments?.getString("addressId")?.toInt()
            AddEditAddressScreen(navController, addressViewModel, addressId)
        }
        composable("comments/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toInt()
            if (productId != null) {
                CommentsScreen(navController, productId)
            }
        }
        composable("account_security") {
            AccountSecurityScreen()
        }
        composable("notification_settings") {
            NotificationSettingsScreen()
        }
        composable("privacy") {
            PrivacyScreen()
        }
        composable("general") {
            GeneralSettingsScreen()
        }
        composable("about_us") {
            AboutUsScreen()
        }
        composable("history") {
            HistoryScreen(navController, historyViewModel, favoritesViewModel)
        }
    }
}