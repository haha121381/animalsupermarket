package com.example.animalsupermarket.repository

import com.example.animalsupermarket.model.Address

class AddressRepository {
    fun getAddresses(): List<Address> {
        return getSampleAddresses()
    }
}