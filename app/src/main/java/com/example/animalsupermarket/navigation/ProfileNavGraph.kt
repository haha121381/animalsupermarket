package com.example.animalsupermarket.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.animalsupermarket.screens.profile.AboutUsScreen
import com.example.animalsupermarket.screens.profile.AccountSecurityScreen
import com.example.animalsupermarket.screens.profile.AddEditAddressScreen
import com.example.animalsupermarket.screens.profile.AddressScreen
import com.example.animalsupermarket.screens.profile.CouponsScreen
import com.example.animalsupermarket.screens.profile.GeneralSettingsScreen
import com.example.animalsupermarket.screens.profile.HistoryScreen
import com.example.animalsupermarket.screens.profile.NotificationSettingsScreen
import com.example.animalsupermarket.screens.profile.PrivacyScreen
import com.example.animalsupermarket.screens.profile.SettingsScreen
import com.example.animalsupermarket.viewmodel.FavoritesViewModel
import com.example.animalsupermarket.viewmodel.AddressViewModel
import com.example.animalsupermarket.viewmodel.CouponsViewModel
import com.example.animalsupermarket.viewmodel.HistoryViewModel

fun NavGraphBuilder.profileNavGraph(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel
) {
    navigation(
        startDestination = "profile",
        route = "profileGraph"
    ) {
        composable("address") {
            val addressViewModel: AddressViewModel = hiltViewModel()
            AddressScreen(navController, addressViewModel)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
        composable("coupons") {
            val couponsViewModel: CouponsViewModel = hiltViewModel()
            CouponsScreen(couponsViewModel)
        }
        composable("addEditAddress") {
            val addressViewModel: AddressViewModel = hiltViewModel()
            AddEditAddressScreen(navController, addressViewModel)
        }
        composable(
            "addEditAddress/{addressId}",
            arguments = listOf(navArgument("addressId") { type = NavType.IntType })
        ) { backStackEntry ->
            val addressId = backStackEntry.arguments?.getInt("addressId")
            val addressViewModel: AddressViewModel = hiltViewModel()
            AddEditAddressScreen(navController, addressViewModel, addressId)
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
            val historyViewModel: HistoryViewModel = hiltViewModel()
            HistoryScreen(navController, historyViewModel, favoritesViewModel)
        }
    }
}