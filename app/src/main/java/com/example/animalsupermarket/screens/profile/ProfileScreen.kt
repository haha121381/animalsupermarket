package com.example.animalsupermarket.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import com.example.animalsupermarket.viewmodel.ProfileViewModel
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalsupermarket.viewmodel.AuthViewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    val user by profileViewModel.user.collectAsState()
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我的") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (authState.isLoggedIn) {
                // User Info Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(user?.avatarUrl),
                        contentDescription = user?.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(user?.name ?: "未登录", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Text("查看并编辑个人资料", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                    }
                }
            } else {
                // Login/Register Button
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { navController.navigate("login") }) {
                        Text("登录/注册")
                    }
                }
            }

            HorizontalDivider()

            // Order and Coupon Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate("orders") }
                ) {
                    Text(user?.orderCount.toString(), style = MaterialTheme.typography.titleLarge)
                    Text("我的订单", style = MaterialTheme.typography.bodyMedium)
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate("coupons") }
                ) {
                    Text(user?.couponCount.toString(), style = MaterialTheme.typography.titleLarge)
                    Text("优惠券", style = MaterialTheme.typography.bodyMedium)
                }
            }

            HorizontalDivider()

            // Menu List
            Column {
                ProfileMenuItem("地址管理", icon = Icons.Default.LocationOn, onClick = { navController.navigate("address") })
                ProfileMenuItem("我的订单", icon = Icons.Default.Receipt, onClick = { navController.navigate("orders") })
                ProfileMenuItem("我的收藏", icon = Icons.Default.Favorite, onClick = { navController.navigate("favorites") })
                ProfileMenuItem("历史足迹", icon = Icons.Default.History, onClick = { navController.navigate("history") })
                ProfileMenuItem("联系客服", icon = Icons.Default.HeadsetMic, onClick = {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:40012345678")
                    context.startActivity(intent)
                })
                ProfileMenuItem("设置", icon = Icons.Default.Settings, onClick = { navController.navigate("settings") })
            }
        }
    }
}

@Composable
fun ProfileMenuItem(title: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = null)
    }
}