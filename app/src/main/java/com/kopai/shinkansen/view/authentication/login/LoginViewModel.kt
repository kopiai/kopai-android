package com.kopai.shinkansen.view.authentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val storiesRepository: StoriesRepository
) :
    ViewModel() {
    fun saveSession(user: UserPrefModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String) = storiesRepository.login(email, password)

}
