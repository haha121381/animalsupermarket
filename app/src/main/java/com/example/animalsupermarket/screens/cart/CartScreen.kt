package com.example.animalsupermarket.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.animalsupermarket.model.Coupon
import com.example.animalsupermarket.model.Product
import com.example.animalsupermarket.ui.theme.*
import com.example.animalsupermarket.viewmodel.CartItem
import com.example.animalsupermarket.viewmodel.CartViewModel
import com.example.animalsupermarket.viewmodel.CouponsViewModel
import com.example.animalsupermarket.viewmodel.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    navController: NavController,
    orderViewModel: OrderViewModel = hiltViewModel(),
    couponsViewModel: CouponsViewModel = hiltViewModel()
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val coupons by couponsViewModel.coupons.collectAsState()
    var selectedCoupon by remember { mutableStateOf<Coupon?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("购物车") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background)
            )
        },
        containerColor = Background,
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                CheckoutBar(
                    totalPrice = cartViewModel.getTotalPrice(selectedCoupon),
                    itemCount = cartItems.sumOf { it.quantity },
                    onCheckout = {
                        orderViewModel.placeOrder(cartItems, cartViewModel.getTotalPrice(selectedCoupon))
                        cartViewModel.clearCart()
                        navController.navigate("orders")
                    }
                )
            }
        }
    ) { paddingValues ->
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("购物车是空的")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                items(cartItems) { cartItem ->
                    CartListItem(
                        cartItem = cartItem,
                        onIncrease = { cartViewModel.increaseQuantity(cartItem.product.id) },
                        onDecrease = { cartViewModel.decreaseQuantity(cartItem.product.id) },
                        onRemove = { cartViewModel.removeFromCart(cartItem.product.id) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                item {
                    RecommendedForYou()
                }
            }
        }
    }
}

@Composable
fun CartListItem(
    cartItem: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = true, // Add logic for selection
                onCheckedChange = { /* TODO */ },
                colors = CheckboxDefaults.colors(checkedColor = Primary)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = rememberAsyncImagePainter(cartItem.product.imageUrl),
                contentDescription = cartItem.product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    cartItem.product.name,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "15公斤装", // Example, replace with actual product variant
                    style = MaterialTheme.typography.bodySmall,
                    color = TextColorSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "¥ ${cartItem.product.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                IconButton(onClick = onRemove) {
                    Icon(Icons.Default.Delete, contentDescription = "Remove", tint = IconColor)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onDecrease, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrease")
                    }
                    Text(
                        cartItem.quantity.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = onIncrease, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.Add, contentDescription = "Increase")
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendedForYou() {
    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(
            "为你推荐",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(3) { index ->
                RecommendedItem(
                    product = Product(
                        id = index,
                        name = "商品推荐${index + 1}",
                        description = "这是一个推荐商品",
                        price = 99.0,
                        imageUrl = "https://example.com/product.png",
                        category = "推荐",
                        sales = 120,
                        monthlySales = 30,
                        rating = 4.8,
                        brand = "推荐品牌",
                        stock = 50,
                        specifications = listOf("规格A", "规格B")
                    )
                )
            }
        }
    }
}

@Composable
fun RecommendedItem(product: Product) {
    Card(
        modifier = Modifier.width(120.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for product image
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                product.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "¥${product.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = Primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CheckoutBar(totalPrice: Double, itemCount: Int, onCheckout: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = true, // Add logic for select all
                    onCheckedChange = { /* TODO */ },
                    colors = CheckboxDefaults.colors(checkedColor = Primary)
                )
                Text("全选", modifier = Modifier.padding(start = 8.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("合计: ", style = MaterialTheme.typography.bodyLarge)
                Text(
                    "¥ ${String.format("%.2f", totalPrice)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Primary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = onCheckout,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
                ) {
                    Text("结算($itemCount)", color = White)
                }
            }
        }
    }
}