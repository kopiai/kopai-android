package com.kopai.shinkansen.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.remote.response.ProductItem
import com.kopai.shinkansen.data.repository.ProductsRepository
import com.kopai.shinkansen.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        private val productsRepository: ProductsRepository,
    ) : ViewModel() {
        val productsPaging: LiveData<PagingData<ProductItem>> =
            productsRepository.getProductsPaging().cachedIn(viewModelScope)

        fun getProducts() = productsRepository.getProductsWithLocation()

        fun getSession(): LiveData<UserPrefModel> {
            return userRepository.getSession().asLiveData()
        }

        fun logout() {
            viewModelScope.launch {
                userRepository.logout()
            }
        }
    }
