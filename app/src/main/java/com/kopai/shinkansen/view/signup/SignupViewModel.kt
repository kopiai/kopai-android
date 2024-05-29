package com.kopai.shinkansen.view.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kopai.shinkansen.data.pref.UserModel
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import kotlinx.coroutines.launch

class SignupViewModel(
    private val storiesRepository: StoriesRepository
) :
    ViewModel() {
    fun register(name: String, email: String, password: String) =
        storiesRepository.register(name, email, password)

}
