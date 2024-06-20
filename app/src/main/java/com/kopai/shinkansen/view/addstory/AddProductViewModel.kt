package com.kopai.shinkansen.view.addproduct

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel
    @Inject
    constructor(
        private val productsRepository: ProductsRepository,
    ) : ViewModel() {
        fun uploadProduct(
            imageProduct: File,
            description: String,
        ) = productsRepository.uploadProduct(imageProduct, description)
    }
