package com.kopai.shinkansen.view.authentication.login

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val userRepository: UserRepository,
    ) :
    ViewModel() {
        fun login(
            email: String,
            password: String,
        ) = userRepository.login(email, password)
    }
