package com.example.animalsupermarket.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.animalsupermarket.viewmodel.AuthViewModel
import com.example.animalsupermarket.viewmodel.ProfileViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SettingsItem(title = "账号与安全", onClick = { navController.navigate("account_security") })
        SettingsItem(title = "通知设置", onClick = { navController.navigate("notification_settings") })
        SettingsItem(title = "隐私", onClick = { navController.navigate("privacy") })
        SettingsItem(title = "通用", onClick = { navController.navigate("general") })
        SettingsItem(title = "关于我们", onClick = { navController.navigate("about_us") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authViewModel.logout()
                profileViewModel.clearProfile()
                navController.navigate("welcome") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("退出登录")
        }
    }
}

@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, style = MaterialTheme.typography.bodyLarge)
        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null)
    }
}