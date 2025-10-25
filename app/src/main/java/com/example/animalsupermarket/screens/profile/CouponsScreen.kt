package com.example.animalsupermarket.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalsupermarket.model.Coupon
import com.example.animalsupermarket.viewmodel.CouponsViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CouponsScreen(couponsViewModel: CouponsViewModel = viewModel()) {
    val coupons by couponsViewModel.coupons.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(coupons) { coupon ->
            CouponItem(coupon = coupon)
        }
    }
}

@Composable
fun CouponItem(coupon: Coupon) {
    val isExpired = coupon.expiryDate.before(Date())
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isExpired) Color.LightGray else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(coupon.description, style = MaterialTheme.typography.titleMedium)
            Text(
                "有效期至: ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(coupon.expiryDate)}",
                style = MaterialTheme.typography.bodySmall,
                color = if (isExpired) Color.Gray else Color.Unspecified
            )
        }
    }
}