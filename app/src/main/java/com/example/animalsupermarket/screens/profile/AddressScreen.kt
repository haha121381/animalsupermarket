package com.example.animalsupermarket.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalsupermarket.model.Address
import com.example.animalsupermarket.viewmodel.AddressViewModel
import androidx.navigation.NavController

@Composable
fun AddressScreen(navController: NavController, addressViewModel: AddressViewModel = viewModel()) {
    val addresses by addressViewModel.addresses.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addEditAddress") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Address")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(addresses) { address ->
                AddressItem(
                    address = address,
                    onSetDefault = { addressViewModel.setDefaultAddress(address.id) },
                    onDelete = { addressViewModel.deleteAddress(address.id) },
                    onEdit = { navController.navigate("addEditAddress/${address.id}") }
                )
            }
        }
    }
}

@Composable
fun AddressItem(
    address: Address,
    onSetDefault: () -> Unit,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("${address.recipientName}  ${address.phoneNumber}", style = MaterialTheme.typography.titleMedium)
            Text(address.address)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = address.isDefault, onCheckedChange = { onSetDefault() })
                    Text("默认地址")
                }
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit Address")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Address")
                    }
                }
            }
        }
    }
}