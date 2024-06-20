package com.kopai.shinkansen.view.product.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel
    @Inject
    constructor(
        productsRepository: ProductsRepository,
    ) : ViewModel() {
        val productsPaging: LiveData<PagingData<ProductItem>> =
            productsRepository.getProductsPaging().cachedIn(viewModelScope)
    }
