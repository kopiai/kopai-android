package com.kopai.shinkansen.view.checkout.steps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckoutShippingViewModel : ViewModel() {
    fun getProvinces(): LiveData<Array<String>> {
        return MutableLiveData(
            arrayOf(
                "Aceh",
                "Sumatera Utara",
                "Sumatera Barat",
                "Riau",
                "Jambi",
            ),
        )
    }
}
