package com.example.animalsupermarket.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalsupermarket.screens.cart.CartScreen
import com.example.animalsupermarket.screens.favorites.FavoritesScreen
import com.example.animalsupermarket.screens.home.HomeScreen
import com.example.animalsupermarket.screens.order.OrderScreen
import com.example.animalsupermarket.screens.profile.ProfileScreen
import com.example.animalsupermarket.screens.search.SearchScreen
import com.example.animalsupermarket.viewmodel.AuthViewModel
import com.example.animalsupermarket.viewmodel.HomeViewModel
import com.example.animalsupermarket.viewmodel.ProfileViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    modifier: Modifier = Modifier
) {
    val cartViewModel: CartViewModel = hiltViewModel()
    val favoritesViewModel: FavoritesViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "auth", modifier = modifier) {
        authNavGraph(navController, authViewModel, profileViewModel)
        productNavGraph(navController, cartViewModel, favoritesViewModel)
        profileNavGraph(navController, favoritesViewModel)

        composable("home") {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController, homeViewModel, cartViewModel)
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
            val searchViewModel: SearchViewModel = viewModel()
            SearchScreen(navController, searchViewModel, cartViewModel)
        }
        composable("orders") {
            OrderScreen(navController)
        }
    }
}