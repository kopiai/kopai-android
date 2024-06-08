package com.kopai.shinkansen.view.checkout.steps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckoutPaymentViewModel : ViewModel() {
    fun getAvailablePayments(): LiveData<Array<String>> {
        return MutableLiveData(
            arrayOf(
                "Ovo",
                "Dana",
                "Gopay",
                "Shopeepay",
                "Bank Transfer",
            ),
        )
    }
}
