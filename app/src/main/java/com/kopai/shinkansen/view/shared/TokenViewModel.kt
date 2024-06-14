package com.kopai.shinkansen.view.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.local.pref.UserPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TokenViewModel
    @Inject
    constructor(
        private val userPreference: UserPreference,
    ) : ViewModel() {
        val token = MutableLiveData<UserPrefModel?>()

        init {
            viewModelScope.launch(Dispatchers.IO) {
                userPreference.getSession().collect {
                    withContext(Dispatchers.Main) {
                        token.value = it
                    }
                }
            }
        }

        fun saveToken(
            userId: String,
            email: String,
            token: String,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                userPreference.saveSession(UserPrefModel(userId,email, token))
            }
        }

        fun deleteToken() {
            viewModelScope.launch {
                userPreference.logout()
            }
        }
    }
