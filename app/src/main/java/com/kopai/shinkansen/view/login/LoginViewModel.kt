package com.kopai.shinkansen.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kopai.shinkansen.data.pref.UserModel
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val storiesRepository: StoriesRepository
) :
    ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String) = storiesRepository.login(email, password)

}
