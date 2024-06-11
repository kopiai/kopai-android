package com.kopai.shinkansen.view.authentication.login

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.StoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val storiesRepository: StoriesRepository,
    ) :
    ViewModel() {
        fun login(
            email: String,
            password: String,
        ) = storiesRepository.login(email, password)
    }
