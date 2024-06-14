package com.kopai.shinkansen.view.authentication.login

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val productsRepository: ProductsRepository,
    ) :
    ViewModel() {
        fun login(
            email: String,
            password: String,
        ) = productsRepository.login(email, password)
    }
