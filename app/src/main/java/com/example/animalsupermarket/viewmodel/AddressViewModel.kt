package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.model.Address
import com.example.animalsupermarket.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val addressRepository: AddressRepository) : ViewModel() {

    private val _addresses = MutableStateFlow<List<Address>>(emptyList())
    val addresses: StateFlow<List<Address>> = _addresses.asStateFlow()

    init {
        _addresses.value = addressRepository.getAddresses()
    }

    fun addAddress(address: Address) {
        _addresses.update { it + address }
    }

    fun deleteAddress(addressId: Int) {
        _addresses.update { it.filterNot { address -> address.id == addressId } }
    }

    fun updateAddress(updatedAddress: Address) {
        _addresses.update { addresses ->
            addresses.map { if (it.id == updatedAddress.id) updatedAddress else it }
        }
    }

    fun setDefaultAddress(addressId: Int) {
        _addresses.update { addresses ->
            addresses.map { it.copy(isDefault = it.id == addressId) }
        }
    }
}