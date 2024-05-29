package com.kopai.shinkansen.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kopai.shinkansen.data.ResultState
import com.kopai.shinkansen.data.pref.UserModel
import com.kopai.shinkansen.data.remote.response.StoriesResponse
import com.kopai.shinkansen.data.repository.StoriesRepository
import com.kopai.shinkansen.data.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository,
    private val storiesRepository: StoriesRepository,
) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }

    fun getStories(): LiveData<ResultState<StoriesResponse>> {
        return storiesRepository.getStories()
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
        }
    }
}
