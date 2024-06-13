package com.kopai.shinkansen.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kopai.shinkansen.data.local.pref.UserPrefModel
import com.kopai.shinkansen.data.local.pref.UserPreference
import kotlinx.coroutines.launch

class SplashViewModel(private val pref: UserPreference) : ViewModel() {


    fun getUserPreferences(): LiveData<UserPrefModel> {
        return pref.getSession().asLiveData()
    }

    fun setUserPreferences(user: UserPrefModel) {
        viewModelScope.launch {
            pref.saveSession(user)
        }
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

    class Factory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = SplashViewModel(pref) as T
    }
}