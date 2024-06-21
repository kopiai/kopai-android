package com.kopai.shinkansen.view.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.repository.ProductsRepository
import com.kopai.shinkansen.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainHomeViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
        private val productsRepository: ProductsRepository,
    ) : ViewModel() {
        fun getNews() = productsRepository.getNews()

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
