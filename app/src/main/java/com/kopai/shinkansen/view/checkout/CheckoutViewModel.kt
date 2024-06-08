package com.kopai.shinkansen.view.checkout

import androidx.lifecycle.ViewModel

class CheckoutViewModel : ViewModel() {
    fun getCheckoutStatus(): String {
        return "Payment"
    }
}
