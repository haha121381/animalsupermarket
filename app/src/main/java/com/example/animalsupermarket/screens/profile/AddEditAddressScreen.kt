package com.example.animalsupermarket.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animalsupermarket.model.Address
import com.example.animalsupermarket.viewmodel.AddressViewModel

@Composable
fun AddEditAddressScreen(
    navController: NavController,
    addressViewModel: AddressViewModel,
    addressId: Int? = null
) {
    val isEditing = addressId != null
    val address = if (isEditing) {
        addressViewModel.addresses.collectAsState().value.find { it.id == addressId }
    } else {
        null
    }

    var recipientName by remember { mutableStateOf(address?.recipientName ?: "") }
    var phoneNumber by remember { mutableStateOf(address?.phoneNumber ?: "") }
    var addressText by remember { mutableStateOf(address?.address ?: "") }
    var isDefault by remember { mutableStateOf(address?.isDefault ?: false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = recipientName,
            onValueChange = { recipientName = it },
            label = { Text("收件人") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("手机号码") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = addressText,
            onValueChange = { addressText = it },
            label = { Text("详细地址") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(checked = isDefault, onCheckedChange = { isDefault = it })
            Text("设为默认地址")
        }
        Button(
            onClick = {
                val newAddress = Address(
                    id = addressId ?: (addressViewModel.addresses.value.maxOfOrNull { it.id } ?: 0) + 1,
                    recipientName = recipientName,
                    phoneNumber = phoneNumber,
                    address = addressText,
                    isDefault = isDefault
                )
                if (isEditing) {
                    addressViewModel.updateAddress(newAddress)
                } else {
                    addressViewModel.addAddress(newAddress)
                }
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isEditing) "保存" else "添加")
        }
    }
}