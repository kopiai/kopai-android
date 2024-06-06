package com.kopai.shinkansen.view.signup

import androidx.lifecycle.ViewModel
import com.kopai.shinkansen.data.repository.StoriesRepository

class SignupViewModel(
    private val storiesRepository: StoriesRepository
) :
    ViewModel() {
    fun register(name: String, email: String, password: String) =
        storiesRepository.register(name, email, password)

}
