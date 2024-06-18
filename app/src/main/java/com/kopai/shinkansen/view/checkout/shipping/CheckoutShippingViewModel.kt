package com.kopai.shinkansen.view.checkout.shipping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutShippingViewModel
    @Inject
    constructor(
        private val productsRepository: ProductsRepository,
    ) :
    ViewModel() {
        fun updateUser(
            name: String,
            phone: String,
            address: String,
        ) = productsRepository.updateUser(
            name,
            phone,
            address,
        )

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
