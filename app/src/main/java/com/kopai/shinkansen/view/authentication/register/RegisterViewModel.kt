package com.kopai.shinkansen.view.authentication.register

import androidx.lifecycle.ViewModel
<<<<<<< HEAD
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
=======
import com.kopai.shinkansen.data.repository.ProductsRepository
>>>>>>> main
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
    ) :
    ViewModel() {
        fun register(
            name: String,
            email: String,
            password: String,
        ) = userRepository.register(name, email, password)
    }
