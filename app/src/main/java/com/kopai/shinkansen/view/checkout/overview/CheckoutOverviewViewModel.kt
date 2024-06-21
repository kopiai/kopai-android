package com.kopai.shinkansen.view.checkout.overview

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.remote.response.OrderItemResponse
import com.kopai.shinkansen.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutOverviewViewModel
    @Inject
    constructor(
        private val productsRepository: ProductsRepository,
    ) : ViewModel() {
        fun createOrder(orderItems: List<OrderItemResponse>) = productsRepository.createOrder(orderItems)
    }
