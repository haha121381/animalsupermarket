package com.example.animalsupermarket.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.animalsupermarket.screens.auth.LoginScreen
import com.example.animalsupermarket.screens.auth.RegisterScreen
import com.example.animalsupermarket.screens.welcome.WelcomeScreen
import com.example.animalsupermarket.viewmodel.AuthViewModel
import com.example.animalsupermarket.viewmodel.ProfileViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavController,
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel
) {
    navigation(
        startDestination = "welcome",
        route = "auth"
    ) {
        composable("welcome") {
            WelcomeScreen(navController, authViewModel)
        }
        composable("login") {
            LoginScreen(navController, authViewModel, profileViewModel)
        }
        composable("register") {
            RegisterScreen(navController, authViewModel, profileViewModel)
        }
    }
}