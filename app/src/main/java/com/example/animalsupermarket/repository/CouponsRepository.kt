package com.example.animalsupermarket.repository

import com.example.animalsupermarket.model.Coupon
import java.util.Calendar
import java.util.Date

class CouponsRepository {
    fun getCoupons(): List<Coupon> {
        return getSampleCoupons()
    }
}