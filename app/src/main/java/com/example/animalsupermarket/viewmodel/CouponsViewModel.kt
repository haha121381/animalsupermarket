package com.example.animalsupermarket.viewmodel

import androidx.lifecycle.ViewModel
import com.example.animalsupermarket.model.Coupon
import com.example.animalsupermarket.repository.CouponsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CouponsViewModel @Inject constructor(private val couponsRepository: CouponsRepository) : ViewModel() {

    private val _coupons = MutableStateFlow<List<Coupon>>(emptyList())
    val coupons: StateFlow<List<Coupon>> = _coupons.asStateFlow()

    init {
        _coupons.value = couponsRepository.getCoupons()
    }
}