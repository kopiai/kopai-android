package com.kopai.shinkansen.view.authentication.register

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
    @Inject
    constructor(
        private val productsRepository: ProductsRepository,
    ) :
    ViewModel() {
        fun register(
            name: String,
            email: String,
            password: String,
        ) = productsRepository.register(name, email, password)
    }
