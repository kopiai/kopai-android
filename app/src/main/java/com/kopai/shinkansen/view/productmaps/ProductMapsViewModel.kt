package com.kopai.shinkansen.view.productmaps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.remote.response.ProductsResponse
import com.kopai.shinkansen.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductMapsViewModel
    @Inject
    constructor(
        private val productsRepository: ProductsRepository,
    ) : ViewModel() {
        fun getProductsWithLocation(): LiveData<ResultState<ProductsResponse>> {
            return productsRepository.getProductsWithLocation()
        }
    }
