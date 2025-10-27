package com.example.animalsupermarket.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animalsupermarket.ui.theme.*
import com.example.animalsupermarket.viewmodel.AuthViewModel
import com.example.animalsupermarket.viewmodel.ProfileViewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val user by profileViewModel.user.collectAsState()
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        containerColor = Background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (authState.isLoggedIn) {
                UserInfoHeader(
                    onEditProfile = { /* TODO */ },
                    points = 128,
                    coupons = "50日元",
                    orders = 15
                )
            } else {
                LoginRegisterButton(navController)
            }

            Spacer(modifier = Modifier.height(16.dp))

            MyOrdersSection(
                onViewAllOrders = { navController.navigate("orders") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            MenuList(navController, context)
        }
    }
}

@Composable
fun UserInfoHeader(
    onEditProfile: () -> Unit,
    points: Int,
    coupons: String,
    orders: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Primary,
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://example.com/avatar.png"), // Replace with actual avatar URL
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "宠物爱好者",
                    color = White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "会员等级：黄金会员",
                    color = White,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onEditProfile,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("编辑资料", color = White)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoColumn(title = "积分", value = points.toString())
            InfoColumn(title = "优惠券", value = coupons)
            InfoColumn(title = "订单", value = orders.toString())
        }
    }
}

@Composable
fun InfoColumn(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, color = White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = title, color = White, fontSize = 14.sp)
    }
}

@Composable
fun LoginRegisterButton(navController: NavController) {
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

@Composable
fun MyOrdersSection(onViewAllOrders: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("我的订单", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(onClick = onViewAllOrders)
                ) {
                    Text("查看全部", style = MaterialTheme.typography.bodyMedium, color = TextColorSecondary)
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = null,
                        tint = TextColorSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OrderActionItem(icon = Icons.Default.Wallet, text = "待付款", badgeCount = 2)
                OrderActionItem(icon = Icons.AutoMirrored.Filled.LocalShipping, text = "待收货", badgeCount = 1)
                OrderActionItem(icon = Icons.Default.Star, text = "待评价", badgeCount = 3)
                OrderActionItem(icon = Icons.Default.SwapHoriz, text = "退换货")
            }
        }
    }
}

@Composable
fun OrderActionItem(icon: ImageVector, text: String, badgeCount: Int = 0) {
    // This is a simplified version. A real implementation would use a BadgeBox.
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { /* TODO */ }
    ) {
        Icon(icon, contentDescription = text, tint = IconColor)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun MenuList(navController: NavController, context: android.content.Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column {
            ProfileMenuItem(
                title = "我的订单",
                subtitle = "查看全部订单",
                icon = Icons.Default.Lock,
                onClick = { navController.navigate("orders") }
            )
            ProfileMenuItem(
                title = "我的收藏",
                subtitle = "收藏的商品",
                icon = Icons.Default.FavoriteBorder,
                onClick = { navController.navigate("favorites") }
            )
            ProfileMenuItem(
                title = "收货地址",
                subtitle = "管理收货地址",
                icon = Icons.Default.LocationOn,
                onClick = { navController.navigate("address") }
            )
            ProfileMenuItem(
                title = "在线客服",
                subtitle = "7x24小时服务",
                icon = Icons.Default.HeadsetMic,
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:40012345678")
                    context.startActivity(intent)
                }
            )
            ProfileMenuItem(
                title = "设置",
                subtitle = "账号与安全",
                icon = Icons.Default.Settings,
                onClick = { navController.navigate("settings") }
            )
        }
    }
}

@Composable
fun ProfileMenuItem(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier
                .size(40.dp)
                .background(color = Color.LightGray.copy(alpha = 0.2f), shape = CircleShape)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = TextColorSecondary)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = TextColorSecondary
        )
    }
}